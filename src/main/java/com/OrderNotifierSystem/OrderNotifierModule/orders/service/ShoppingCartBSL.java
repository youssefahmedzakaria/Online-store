package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.ShoppingCart;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@NoArgsConstructor
public class ShoppingCartBSL {


    private final static ShoppingCart shoppingCart = new ShoppingCart();
    ProductBSL productBSL = new ProductBSL();
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String addToCart(String productName, int Quantity) {
        Product product = productBSL.findProduct(productName);
        if (product != null) {
            if (product.getQuantity() >= Quantity) {
                if (shoppingCart.getCart() == null) {
                    shoppingCart.setTotalCost((float) (product.getPrice() * Quantity));
                    product.setQuantity(product.getQuantity() - Quantity);
                } else {
                    shoppingCart.setTotalCost((float) (shoppingCart.getTotalCost() + product.getPrice() * Quantity));
                    product.setQuantity(product.getQuantity() - Quantity);
                    shoppingCart.setQuantity(shoppingCart.getQuantity() + Quantity);
                }
            } else {
                return "Quantity not available";
            }
        } else {
            return "Product not found";
        }
        Product cartProduct = new Product(product.getName(), Quantity, product.getPrice());
        shoppingCart.getCart().add(cartProduct);
        return Quantity + " " + productName+ "(s)" + " added to cart";
    }

    public ArrayList<String> DisplayCart() {
        ArrayList<String> cartProducts = new ArrayList<>();
        for (Product product : shoppingCart.getCart()) {
            String cartProduct = product.getName() + " x " + product.getQuantity() + " = $" + product.getPrice() * product.getQuantity();
            cartProducts.add(cartProduct);
        }
        return cartProducts;
    }
    public String removeProduct(String productName) {
        Product product = productBSL.findProduct(productName);
        if (product.getName() != null) {
            for (Product cartProduct : shoppingCart.getCart()) {
                if (cartProduct.getName().equals(productName)) {
                    shoppingCart.setTotalCost((float) (shoppingCart.getTotalCost() - cartProduct.getPrice() * cartProduct.getQuantity()));
                    product.setQuantity(product.getQuantity() + cartProduct.getQuantity());
                    shoppingCart.getCart().remove(cartProduct);
                    return productName + " removed from cart";
                }
            }
        } else {
            return "Product not found in cart";
        }
        return "Product not found";
    }

    public void clearCart() {
        shoppingCart.getCart().clear();
        shoppingCart.setTotalCost(0);
        shoppingCart.setQuantity(0);
    }


    public String getTotalCost() {
        return "Total Cost: " + "&" +shoppingCart.getTotalCost();
    }
}

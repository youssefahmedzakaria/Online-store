package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.ShoppingCart;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import java.util.ArrayList;


public class ShoppingCartBSL {

    private ShoppingCart shoppingCart = new ShoppingCart();
    public String addToCart(String productName, int Quantity) {
        ProductBSL productBSL = new ProductBSL();
        Product product = productBSL.findProduct(productName);
        if (product.getName() != null) {
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
    public float getTotalCost() {
        return shoppingCart.getTotalCost();
    }
}

package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.ShoppingCart;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@NoArgsConstructor
public class ShoppingCartImp {
   private final static ProductDBImp productBSL = new ProductDBImp();
    private final static UsersImp user = new UsersImp();

    public String addToCart(String username, String productName, int Quantity) {
        if(!user.checkUser(username)){
            return "User not found";
        }
        Product product = productBSL.findProduct(productName);
        if (product != null) {
            if (product.getQuantity() >= Quantity) {
                if (user.getUser(username).getShoppingCart().getCart() == null) {
                    user.getUser(username).getShoppingCart().setTotalCost((float) (product.getPrice() * Quantity));
                    product.setQuantity(product.getQuantity() - Quantity);
                } else {
                    user.getUser(username).getShoppingCart().setTotalCost((float) (user.getUser(username).getShoppingCart().getTotalCost() + product.getPrice() * Quantity));
                    product.setQuantity(product.getQuantity() - Quantity);
                    user.getUser(username).getShoppingCart().setQuantity(user.getUser(username).getShoppingCart().getQuantity() + Quantity);
                }
            } else {
                return "Quantity not available";
            }
        } else {
            return "Product not found";
        }
        Product cartProduct = new Product(product.getName(), Quantity, product.getPrice());
        user.getUser(username).getShoppingCart().getCart().add(cartProduct);
        return Quantity + " " + productName+ "(s)" + " added to cart";
    }

    public ArrayList<String> DisplayCart(String username) {
        if(!user.checkUser(username)){
            return null;
        }
        ArrayList<String> cartProducts = new ArrayList<>();
        for (Product product : user.getUser(username).getShoppingCart().getCart()) {
            String cartProduct = product.getName() + " x " + product.getQuantity() + " = $" + product.getPrice() * product.getQuantity();
            cartProducts.add(cartProduct);
        }
        return cartProducts;
    }
    public String removeFromCart(String username,String productName) {
        if(!user.checkUser(username)){
            return "User not found";
        }
        Product product = productBSL.findProduct(productName);
        if (product.getName() != null) {
            for (Product cartProduct : user.getUser(username).getShoppingCart().getCart()) {
                if (cartProduct.getName().equals(productName)) {
                    user.getUser(username).getShoppingCart().setTotalCost((float) (user.getUser(username).getShoppingCart().getTotalCost() - cartProduct.getPrice() * cartProduct.getQuantity()));
                    product.setQuantity(product.getQuantity() + cartProduct.getQuantity());
                    user.getUser(username).getShoppingCart().getCart().remove(cartProduct);
                    return productName + " removed from cart";
                }
            }
        } else {
            return "Product not found in cart";
        }
        return "Product not found";
    }

    public void clearCart(String username){
        user.getUser(username).getShoppingCart().getCart().clear();
        user.getUser(username).getShoppingCart().setTotalCost(0);
        user.getUser(username).getShoppingCart().setQuantity(0);
    }



    public float getTotalCost(String username) {
        return user.getUser(username).getShoppingCart().getTotalCost();
    }
    public UsersImp getUserImp() {
        return user;
    }
}

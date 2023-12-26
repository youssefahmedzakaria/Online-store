package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.ShoppingCart;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ShoppingCartBSL {

    private final List<ShoppingCart> shoppingCarts;
    private final UsersBSL usersBSL;

    public ShoppingCartBSL(List<ShoppingCart> shoppingCarts, UsersBSL usersBSL) {
        this.shoppingCarts = shoppingCarts;
        this.usersBSL = usersBSL;
    }

    public void addToCart(User user, Product product) {
        ShoppingCart shoppingCart = getShoppingCart(user.getUsername());
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(user);
            shoppingCart.getCart().add(product);
            shoppingCart.setTotalCost(product.getPrice());
            shoppingCarts.add(shoppingCart);
        } else {
            shoppingCart.getCart().add(product);
            shoppingCart.setTotalCost(shoppingCart.getTotalCost() + product.getPrice());
        }
    }

    public ShoppingCart getShoppingCart(String username) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.getUser().getUsername().equals(username)) {
                return shoppingCart;
            }
        }
        return null;
    }

}

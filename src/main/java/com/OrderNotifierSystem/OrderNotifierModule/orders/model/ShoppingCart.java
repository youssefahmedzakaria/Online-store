package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import java.util.*;


public class ShoppingCart {
    private User user;
    private List<Product> cart;
    private double totalCost;


    public ShoppingCart(User user) {
        this.user = user;
        this.cart = new ArrayList<>();
        this.totalCost = 0;
    }

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public List<Product> getCart() {
        return cart;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

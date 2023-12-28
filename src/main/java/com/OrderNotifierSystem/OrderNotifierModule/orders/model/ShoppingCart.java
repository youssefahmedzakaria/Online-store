package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;

import java.util.ArrayList;

public class ShoppingCart {
    public ArrayList<Product> cart = new ArrayList<>();
    private float totalCost;
    private int quantity;

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
    public ArrayList<Product> getCart() {
        return cart;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> cart = new ArrayList<>();
    private float totalCost;
    private int quantity;


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

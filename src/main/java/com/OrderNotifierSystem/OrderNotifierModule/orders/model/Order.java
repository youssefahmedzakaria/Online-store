package com.OrderNotifierSystem.OrderNotifierModule.orders.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Order {

    private int orderId;
    private User user = new User();
    public ArrayList<Product> order = new ArrayList<>();
    private float totalCost;

    public Order(int orderId, User user, ArrayList<Product> order, float totalCost) {
        this.orderId = orderId;
        this.user = user;
        this.order = order;
        this.totalCost = totalCost;
    }

    public Order() {
    }

    public ArrayList<Product> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Product> order) {
        this.order = order;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

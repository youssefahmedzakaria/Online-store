package com.OrderNotifierSystem.OrderNotifierModule.orders.model;

import org.springframework.stereotype.Component;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartBSL;

@Component
public class Order {
    private int orderId = 0;
    boolean orderStatus = false;

    private User user = new User();

    private ShoppingCartBSL shoppingCartBSL = new ShoppingCartBSL();


    public ShoppingCartBSL getShoppingCartBSL() {
        return shoppingCartBSL;
    }
    public User getUser() {
        return user;
    }



    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean getStatus() {
        return orderStatus;
    }

    public void setStatus(boolean status) {
        this.orderStatus = status;
    }
    public Order() {
    }


}







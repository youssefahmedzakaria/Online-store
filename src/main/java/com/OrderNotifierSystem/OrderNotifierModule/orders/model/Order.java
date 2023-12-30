package com.OrderNotifierSystem.OrderNotifierModule.orders.model;

import org.springframework.stereotype.Component;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartImp;

import java.time.LocalDateTime;

@Component
public class Order {
    private LocalDateTime placementTime;

    // Constructor and other methods

    private boolean isPlaced = false;

    private boolean isShipped = false;
    private int orderId = 0;
    private int compoundOrderId = 0;
    Boolean orderStatus = false;

    private int shippingFees = 15;

    private final static User user = new User();


    private final static ShoppingCartImp shoppingCartBSL = new ShoppingCartImp();
    public Order() {
    }
    public boolean getShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }
    public boolean getPlaced() {
        return isPlaced;
    }
    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }


    public ShoppingCartImp getShoppingCartBSL() {
        return shoppingCartBSL;
    }
    public static User getUser() {
        return user;
    }

    public int getShippingFees() {
        return shippingFees;
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

    public LocalDateTime getShipmentTime() {return placementTime;}

    public void setPlacementTime(LocalDateTime placementTime) {this.placementTime = placementTime;}
    public int getCompoundOrderId() {
        return compoundOrderId;
    }
    public void setCompoundOrderId(int compoundOrderId) {
        this.compoundOrderId = compoundOrderId;
    }


}







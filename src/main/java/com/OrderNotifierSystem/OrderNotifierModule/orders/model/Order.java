package com.OrderNotifierSystem.OrderNotifierModule.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartImp;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class Order {
    private LocalDateTime placementTime;

    public LocalDateTime getCompoundOrderTime() {
        return CompoundOrderTime;
    }

    public void setCompoundOrderTime(LocalDateTime compoundOrderTime) {
        CompoundOrderTime = compoundOrderTime;
    }

    //create a local time variable for compound order
    private LocalDateTime CompoundOrderTime;
    // Constructor and other methods
    static int counter = 1;
    private boolean isPlaced = false;

    private boolean isShipped = false;
    private int orderId = 0;
    private int compoundOrderId = 0;
    Boolean orderStatus = false;
    private int shippingFees = 15;
    private final static User user = new User();
    private final static ShoppingCartImp shoppingCartBSL = new ShoppingCartImp();

    public Order() {
        orderId = counter;
        counter++;
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







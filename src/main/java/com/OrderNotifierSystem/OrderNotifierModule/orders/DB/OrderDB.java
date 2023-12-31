package com.OrderNotifierSystem.OrderNotifierModule.orders.DB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

import java.util.*;

public class OrderDB {
    private  final ArrayList<Order> orders = new ArrayList<>();
    private static final ArrayList<Order> compoundOrders = new ArrayList<>();
    static final Map<Integer, ArrayList<String>> orderMap = new HashMap<>();
    static final Map<Integer, Float> orderTotalCosts = new HashMap<>();
    static final Map<Integer, Float> CompoundOrderTotalCosts = new HashMap<>();


    public  ArrayList<Order> getOrders() {
        return orders;
    }
    public static Map<Integer, ArrayList<String>> getOrderMap() {
        return orderMap;
    }
    public static Map<Integer, Float> getOrderTotalCost() {
        return orderTotalCosts;
    }
    public static Map<Integer, Float> getCompoundOrderTotalCosts() {
        return CompoundOrderTotalCosts;
    }

    public static ArrayList<Order> getCompoundOrder() {
        return compoundOrders;
    }

    public Order getOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
    public boolean findOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }
    public boolean findCompoundOrder(int orderId) {
        for (Order order : compoundOrders) {
            if (order.getCompoundOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }




}

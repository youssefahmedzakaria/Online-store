package com.OrderNotifierSystem.OrderNotifierModule.orders.DB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

import java.util.*;

public class OrderDB {
    private static final ArrayList<Order> orders = new ArrayList<>();
    private static final ArrayList<Product> orderedProducts = new ArrayList<>();
    static final Map<Integer, ArrayList<String>> orderMap = new HashMap<>();
    static final Map<Integer, Float> orderTotalCosts = new HashMap<>();


    public static ArrayList<Order> getOrders() {
        return orders;
    }
    public static ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }
    public static Map<Integer, ArrayList<String>> getOrderMap() {
        return orderMap;
    }
    public static Map<Integer, Float> getOrderTotalCost() {
        return orderTotalCosts;
    }
    public boolean findOrder(int orderId) {
        for (Order order : getOrders()) {
            if (order.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }



}

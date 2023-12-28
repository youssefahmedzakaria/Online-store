//package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
//import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
//import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//
//@Service
//public class OrderBSL {
//    @Autowired
//    private Order order;
//    List<Map<String, Object>> selectedAttributesList = new ArrayList<>();
//    private final ArrayList<Order> orders = new ArrayList<>();
//    public OrderBSL() {
//    }
//
//    public String placeOrder() {
//        if (order.getShoppingCartBSL().getShoppingCart().getCart().isEmpty()) {
//            return "Cart is empty";
//        }
//        order.setOrderId(order.getOrderId() + 1);
//        order.setStatus(true);
//        orders.add(order);
//        return "Order placed successfully";
//    }
//
//    public String cancelOrder(int orderId) {
//        for (Order order : order.getUser().getOrders()) {
//            if (order.getOrderId() == orderId) {
//                order.getUser().getOrders().remove(order);
//                order.setStatus(false);
//                return "Order Cancelled";
//            }
//        }
//        return "Order not found";
//    }
//
//
//    public ArrayList<Product> getOrder(int orderId) {
//        for (Order order : order.getUser().getOrders()) {
//            if (order.getOrderId() == orderId) {
//                return order.getOrder();
//            }
//        }
//        return null;
//    }
//
//    public List<Map<String, Object>> getOrders() {
//        return selectedAttributesList;
//    }
//
//}

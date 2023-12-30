package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CompoundOrder extends Order{
    private ArrayList<SimpleOrder> simpleOrders = new ArrayList<>();

    public void addSimpleOrder(SimpleOrder simpleOrder) {
        simpleOrders.add(simpleOrder);
    }

    public void removeSimpleOrder(SimpleOrder order) {
        simpleOrders.remove(order);
    }









//    public String placeOrders(String username) {
//        placeOrder(username);
//        for (OrderImp order : simpleOrders) {
//            order.placeOrder(order.getOrder().getUser().getUsername());
//        }
//        return "Compound Order placed";
//    }
//
//    public String cancelOrderPlacement(String username, int orderId) {
//        return null;
//    }
//
//    @Override
//    public String shipOrder(String username, int orderId) {
//        return null;
//    }
//
//    @Override
//    public String cancelOrderShipping(String username, int orderId) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<String> getOrder(int orderId) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<Order> getOrders() {
//        return null;
//    }
//
//    @Override
//    public String checkOut(int orderId) {
//        return null;
//    }
//
//    @Override
//    public boolean findOrder(int orderId) {
//        return false;
//    }
//
//    public String cancelCompoundOrder() {
//        for (OrderImp order : simpleOrders) {
//            order.cancelOrderPlacement(order.getOrder().getUser().getUsername(), order.getOrder().getOrderId());
//        }
//        return "Compound Order cancelled";
//    }
//
//
//    public String shipCompoundOrder() {
//        for (OrderImp order : simpleOrders) {
//            order.shipOrder(order.getOrder().getUser().getUsername(), order.getOrder().getOrderId());
//        }
//        return "Compound Order shipped";
//    }
//
//    public String cancelCompoundOrderShipping() {
//        for (OrderImp order : simpleOrders) {
//            order.cancelOrderShipping(order.getOrder().getUser().getUsername(), order.getOrder().getOrderId());
//        }
//        return "Compound Order shipping cancelled";
//    }
//
//    public String checkOut() {
//        for (OrderImp order : simpleOrders) {
//            order.checkOut(order.getOrder().getOrderId());
//        }
//        return "Compound Order checked out";
//    }
//
//
//    public ArrayList<OrderImp> getSimpleOrders() {
//        return simpleOrders;
//    }

}
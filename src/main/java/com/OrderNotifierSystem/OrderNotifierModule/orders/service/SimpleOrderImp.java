package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class SimpleOrderImp extends OrderImp {

    private static final int CANCELLATION_DURATION_LIMIT = 2;
    //create empty cons
    @Override
    public String placeOrder(String username) {
        if (userImp.checkUser(username)) {
            orderDB.getOrderedProducts().clear();
            CopyList(order.getShoppingCartBSL().getShoppingCart().getCart(), orderDB.getOrderedProducts());
            if (order.getShoppingCartBSL().getShoppingCart().getCart().isEmpty()) {
                return "Cart is empty";
            }
            order.setOrderId(order.getOrderId() + 1);
            order.setStatus(true);
            orderDB.getOrders().add(order);
            order.setPlaced(true);
            userImp.getUser(username).getOrders().add(order);
            userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() - order.getShoppingCartBSL().getShoppingCart().getTotalCost());
            return "Order placed successfully \n " + "Your Order ID is: " + order.getOrderId();
        }
        return "User not found";
    }


    @Override
    public String cancelOrderPlacement(String username, int orderId) {
        if (userImp.checkUser(username)) {
            if (order.getPlaced()) {
                if (findOrder(orderId)) {
                    order.setPlaced(false);
                    orderDB.getOrderMap().remove(orderId, orderDB.getOrderMap().get(orderId));
                    for (Product product : orderDB.getOrderedProducts()) {
                        order.getShoppingCartBSL().removeFromCart(product.getName());
                    }
                    order.setStatus(false);
                    // set the user's balance to the previous balance
                    userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() + OrderDB.getOrderTotalCost().get(orderId));
                    return "Order Cancelled";
                }
            }
            return "Order not Placed";
        }
        return "User not found";
    }

    @Override
    public String shipOrder(String username, int orderId) {
        if (userImp.checkUser(username)) {
            if (order.getPlaced()) {
                if (findOrder(orderId)) {
                    order.setShipped(true);
                    order.setPlacementTime(LocalDateTime.now());
                    userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() - order.getShippingFees());
                    return "Order Shipped";
                }
            }
            return "Order not placed";
        }
        return "User not found";
    }


    public String cancelOrderShipping(String username, int orderId) {
        if (userImp.checkUser(username)) {
            if (order.getShipped()) {
                if (findOrder(orderId)) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    LocalDateTime orderShipmentTime = order.getShipmentTime();
                    Duration duration = Duration.between(orderShipmentTime, currentTime);
                    if (duration.toMinutes() <= CANCELLATION_DURATION_LIMIT) {
                        order.setShipped(false);
                        userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() + order.getShippingFees());

                        return "Shipping Cancelled";
                    } else {
                        return "Shipment Cancellation period has expired";
                    }
                }
            }
            return "Order not shipped";
        }
        return "User not found";
    }

    public ArrayList<String> getOrder(int orderId) {
        if (!order.getStatus()) {
            return null;
        }
        ArrayList<String> orderDetails = orderDB.getOrderMap().get(orderId);
        if (orderDetails != null) {
            return orderDetails;
        }
        orderDetails = new ArrayList<>();
        if (findOrder(orderId)) {
            for (Product product : orderDB.getOrderedProducts()) {
                String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                orderDetails.add(orderProduct);
            }
            orderDB.getOrderMap().put(orderId, orderDetails);
            return orderDetails;
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orderDB.getOrders();
    }

    public String checkOut(int orderId) {
        if (order.getPlaced()) {
            if (findOrder(orderId)) {

                order.setShipped(true);
                String cost = String.valueOf(order.getShoppingCartBSL().getShoppingCart().getTotalCost());
                OrderDB.getOrderTotalCost().put(orderId, order.getShoppingCartBSL().getShoppingCart().getTotalCost());
                order.getShoppingCartBSL().clearCart();
                return "Total Cost: " + "$" + cost;
            }
        }
        return "Order not placed";
    }

    public boolean findOrder(int orderId) {
        for (Order order : orderDB.getOrders()) {
            if (order.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }
//make a function get simple order by ID


}


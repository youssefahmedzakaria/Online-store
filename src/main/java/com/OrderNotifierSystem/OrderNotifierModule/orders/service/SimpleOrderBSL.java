package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class SimpleOrderBSL extends OrderBSL {
    private static final int CANCELLATION_DURATION_LIMIT = 2;
    public String placeOrder() {
        orderedProducts.clear();
        // orderedProducts.addAll(order.getShoppingCartBSL().getShoppingCart().getCart());
        CopyList(order.getShoppingCartBSL().getShoppingCart().getCart(), orderedProducts);
        if (order.getShoppingCartBSL().getShoppingCart().getCart().isEmpty()) {
            return "Cart is empty";
        }
        order.setOrderId(order.getOrderId() + 1);
        order.setStatus(true);
        orders.add(order);
        order.setPlaced(true);
        return "Order placed successfully \n " + "Your Order ID is: " + order.getOrderId();
    }

    public String cancelOrderPlacement(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    order.setPlaced(false);
                    orderMap.remove(orderId, orderMap.get(orderId));
                    order.setStatus(false);
                    return "Order Cancelled";
                }
            }
        }
        return "Order not placed";
    }
    public String shipOrder(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    order.setShipped(true);
                    return "Order Shipped";
                }
            }
        }
        return "Order not placed";
    }
    public String cancelOrderShipping(int orderId) {
        if (order.getShipped()) {
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    // Calculate the current time
                    LocalDateTime currentTime = LocalDateTime.now();

                    // Get the order placement time
                    LocalDateTime orderPlacementTime = order.getPlacementTime();

                    // Calculate the duration between order placement and current time
                    Duration duration = Duration.between(orderPlacementTime, currentTime);

                    // Check if the duration is within the pre-configured limit
                    if (duration.toMinutes() <= CANCELLATION_DURATION_LIMIT) {
                        order.setShipped(false);
                        return "Shipping Cancelled";
                    } else {
                        return "Cancellation period has expired";
                    }
                }
            }
        }
        return "Order not placed";
    }

    public ArrayList<String> getOrder(int orderId) {
        if (!order.getStatus()) {
            return null;
        }
        ArrayList<String> orderDetails = orderMap.get(orderId);
        if (orderDetails != null) {
            return orderDetails;
        }
        orderDetails = new ArrayList<>();
        if (order.getOrderId() == orderId) {
            for (Product product : orderedProducts) {
                String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                orderDetails.add(orderProduct);
            }
            orderMap.put(orderId, orderDetails);
            return orderDetails;
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public String checkOut(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    order.setShipped(true);
                    String cost = String.valueOf(order.getShoppingCartBSL().getShoppingCart().getTotalCost());
                    order.getShoppingCartBSL().clearCart();
                    return "Total Cost: " + "$" + cost;
                }
            }
        }
        return "Order not placed";
    }


}

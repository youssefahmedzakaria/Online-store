package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.SimpleOrder;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderBSL {
    private static final int CANCELLATION_DURATION_LIMIT = 2;

    private final OrderDB orderDB = new OrderDB();
    private final UsersImp userImp = new UsersImp();
    private final Order order = new Order();

    public void CopyList(ArrayList<Product> list1, ArrayList<Product> list2) {
        for (Product product : list1) {
            list2.add(product);
        }
    }

    public String placeOrder(String username) {
        orderDB.getOrderedProducts().clear();
        if (userImp.checkUser(username)) {
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
            OrderDB.getOrderTotalCost().put(order.getOrderId(), order.getShoppingCartBSL().getShoppingCart().getTotalCost());
            order.getShoppingCartBSL().clearCart();
            return "Order placed successfully \n " + "Your Order ID is: " + order.getOrderId();
        }
        return "User not found";
    }

    public String placeOrders(ArrayList<String> usernames) {
        for (String username : usernames) {
            if (placeOrder(username).equals("Cart is empty")) {
                return "Cart is empty";
            } else if (placeOrder(username).equals("User not found")) {
                return "User not found";
            }
        }
        orderDB.getCompoundOrder().add(order);
        order.setCompoundOrderId(order.getCompoundOrderId() + 1);
        return "Compound Order placed successfully \n" + "Your Order ID is:  " + order.getCompoundOrderId();
    }

    public String CancelOrdersPlacements(ArrayList<String> usernames, ArrayList<Integer> orderIds, int compoundOrderId) {
        if (orderDB.findCompoundOrder(compoundOrderId)) {
            for (String username : usernames) {
                for (int orderId : orderIds) {
                    if (cancelOrderPlacement(username, orderId).equals("Order not Placed")) {
                        return "Order not Placed";
                    } else if (cancelOrderPlacement(username, orderId).equals("User not found")) {
                        return "User not found";
                    }
                }
            }
            return "Orders Cancelled";
        }
        return "Compound Order not found";
    }

    public String shipOrders(ArrayList<String> usernames, ArrayList<Integer> orderIds, int compoundOrderId) {
        if (orderDB.findCompoundOrder(compoundOrderId)) {
            for (String username : usernames) {
                for (int orderId : orderIds) {
                    if (shipOrder(username, orderId).equals("Order not placed")) {
                        return "Order not placed";
                    } else if (shipOrder(username, orderId).equals("User not found")) {
                        return "User not found";
                    }
                }
            }
            order.setPlacementTime(LocalDateTime.now());
            return "Orders Shipped";
        }
        return "Compound Order not found";
    }

    public String cancelOrdersShipping(ArrayList<String> usernames, ArrayList<Integer> orderIds, int compoundOrderId) {

        if (orderDB.findCompoundOrder(compoundOrderId)) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime orderShipmentTime = order.getShipmentTime();
            Duration duration = Duration.between(orderShipmentTime, currentTime);
            if (duration.toMinutes() <= CANCELLATION_DURATION_LIMIT) {
                for (String username : usernames) {
                    for (int orderId : orderIds) {
                        if (cancelOrderShipping(username, orderId).equals("Order not shipped")) {
                            return "Order not shipped";
                        } else if (cancelOrderShipping(username, orderId).equals("User not found")) {
                            return "User not found";
                        }
                    }
                }
                return "Shipping Cancelled";

            } else {
                return "Shipment Cancellation period has expired";
            }
        }
        return "Compound Order not found";
    }


    public String cancelOrderPlacement(String username, int orderId) {
        if (userImp.checkUser(username)) {
            if (order.getPlaced()) {
                if (orderDB.findOrder(orderId)) {
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

    public String shipOrder(String username, int orderId) {
        if (userImp.checkUser(username)) {
            if (order.getPlaced()) {
                if (orderDB.findOrder(orderId)) {
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
                if (orderDB.findOrder(orderId)) {
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
        if (orderDB.findOrder(orderId)) {
            for (Product product : orderDB.getOrderedProducts()) {
                String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                orderDetails.add(orderProduct);
            }
            orderDB.getOrderMap().put(orderId, orderDetails);
            return orderDetails;
        }
        return null;
    }
    public ArrayList<String> getCompoundOrder(int compoundOrderId) {
        if (orderDB.findCompoundOrder(compoundOrderId)) {
            ArrayList<String> compoundOrderDetails = new ArrayList<>();
            for (Order order : orderDB.getCompoundOrder()) {
                for (Product product : orderDB.getOrderedProducts()) {
                    String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                    compoundOrderDetails.add(orderProduct);
                }
            }
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orderDB.getOrders();
    }

    public String checkOut(int orderId) {
        if (order.getPlaced()) {
            if (orderDB.findOrder(orderId)) {
                order.setShipped(true);
                String cost = String.valueOf(orderDB.getOrderTotalCost().get(orderId) + order.getShippingFees());
                return "Total Cost: " + "$" + cost;
            }
        }
        return "Order not placed";
    }

//    public String checkOutCompoundOrder(ArrayList<Integer> orderIds, int compoundOrderId) {
//        if (orderDB.findCompoundOrder(compoundOrderId)) {
//            float totalCost = 0;
//            for (int orderId : orderIds) {
//                if (orderDB.findOrder(orderId)) {
//                    totalCost += orderDB.getOrderTotalCost().get(orderId);
//                }
//            }
//            totalCost += order.getShippingFees();
//            orderDB.getCompoundOrderTotalCosts().put(compoundOrderId, totalCost);
//            String cost = String.valueOf(totalCost);
//            return "Total Cost: " + "$" + cost;
//        }
//        return "Compound Order not Found";
//    }

}


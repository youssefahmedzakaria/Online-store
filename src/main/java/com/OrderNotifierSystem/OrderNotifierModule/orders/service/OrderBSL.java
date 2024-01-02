package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.controller.ShoppingCartController;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.*;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelShipmentTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderShipmentTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.NotificationsQueue;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderBSL {
    static int counter = 1;
    private static final int CANCELLATION_DURATION_LIMIT = 2;
    private OrderDB orderDB = new OrderDB();
    private  UsersImp userImp = new UsersImp();

    public void CopyList(ArrayList<Product> list1, ArrayList<Product> list2) {
        for (Product product : list1) {
            list2.add(product);
        }
    }
    public String placeOrder(String username) {
        Order order = new Order();
        OrderPlacementTemplate placementNotification = new OrderPlacementTemplate();
        if (userImp.checkUser(username)) {
//            User user = imp.getUser(username);
           // ShoppingCartController shoppingCartController = new ShoppingCartController();
            if (userImp.getUser(username).getShoppingCart().shoppingCart.cart.isEmpty()) {
                return "Cart is empty";
            }
            order.setOrderId(counter++);
//            userImp.getUser(username).getCurrentorder().setOrderId(counter++);
            order.setStatus(true);
            order.setPlaced(true);
            userImp.getUser(username).setCurrentorder(order);
            orderDB.getOrders().add(order);
            userImp.getUser(username).getOrders().add(order);
            userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() - userImp.getUser(username).getShoppingCart().shoppingCart.getTotalCost());
            OrderDB.getOrderTotalCost().put(userImp.getUser(username).getCurrentorder().getOrderId(), userImp.getUser(username).getShoppingCart().shoppingCart.getTotalCost());
            orderDB.getOrdersMap().put(order.getOrderId(), new Order());
            orderDB.getOrdersMap().put(order.getOrderId(), order);
            orderDB.getOrderedProducts().put(order.getOrderId(), new ArrayList<>());
            CopyList(userImp.getUser(username).getShoppingCart().shoppingCart.cart, orderDB.getOrderedProducts().get(userImp.getUser(username).getCurrentorder().getOrderId()));
            userImp.getUser(username).getShoppingCart().shoppingCart.cart.clear();
            //placementNotification.sendOrderPlacementBySMS(username, userImp.getUser(username).getPhone());
            return "Order placed successfully \n " + "Your Order ID is: " + userImp.getUser(username).getCurrentorder().getOrderId();
        }
        return "User not found";
    }

    public String placeOrders(ArrayList<String> usernames) {
        Order order = new Order();
        OrderPlacementTemplate placementNotification = new OrderPlacementTemplate();
        Float TotalCost = 0f;
        ArrayList<Order> individualOrders = new ArrayList<>();
        for (String username : usernames) {
            if(placeOrder(username).equals("Cart is empty")) {
                return "Cart is empty";
            } else if ( placeOrder(username).equals("User not found")) {
                return "User not found";
            }

            placementNotification.sendOrderPlacementBySMS(username, userImp.getUser(username).getPhone());
            Order individualOrder = userImp.getUser(username).getCurrentorder();
            individualOrders.add(individualOrder);
            TotalCost += OrderDB.getOrderTotalCost().get(individualOrder.getOrderId());
        }
        order.setCompoundOrderId(order.getCompoundOrderId() + 1);
        orderDB.getCompoundOrders().put(order.getCompoundOrderId(), new ArrayList<>());
        orderDB.getCompoundOrders().put(order.getCompoundOrderId(), individualOrders);
        orderDB.getCompoundOrderTotalCosts().put(order.getCompoundOrderId(), TotalCost);
        return "Compound Order placed successfully \n" + "Your Order ID is:  " + order.getCompoundOrderId();
    }

    public String CancelOrdersPlacements(int compoundOrderId){
        CancelPlacementTemplate cancelPlacementNotification = new CancelPlacementTemplate();
        if(orderDB.findCompoundOrder(compoundOrderId)) {
            for (Order order : orderDB.getCompoundOrders().get(compoundOrderId)) {
                cancelOrderPlacement(order.getUser().getUsername(), order.getOrderId());
                cancelPlacementNotification.sendCancelPlacementBySMS(order.getUser().getUsername(), order.getUser().getPhone());
            }

            return "Compound Order Cancelled";
        }
        return "Compound Order not found";
    }


//    public String CancelOrdersPlacements(int compoundOrderId){
//        if(orderDB.findCompoundOrder(compoundOrderId)){
//            for (Order order : orderDB.getCompoundOrders().get(compoundOrderId)) {
//                if (cancelOrderPlacement(order.getUser().getUsername(), order.getOrderId()).equals("Order not Placed")) {
//                    return "Order not Placed";
//                } else if (cancelOrderPlacement(order.getUser().getUsername(), order.getOrderId()).equals("User not found")) {
//                    return "User not found";
//                }
//            }
//         }
//        return "Compound Order Cancelled";
//    }

    public String cancelOrderPlacement(String username, int orderId) {
        if (userImp.checkUser(username)) {
            CancelPlacementTemplate cancelPlacementNotification = new CancelPlacementTemplate();
            for (Order order : orderDB.getOrders()) {
                if (orderDB.findOrder(orderId)) {
                    if (order.getPlaced()) {
                        order.setPlaced(false);
                        orderDB.getOrderMap().remove(orderId, orderDB.getOrderMap().get(orderId));
                        for (Product product : userImp.getUser(username).getShoppingCart().shoppingCart.cart) {
                            order.getShoppingCartBSL().removeFromCart(username, product.getName());
                        }
                        order.setStatus(false);
                        // set the user's balance to the previous balance
                        userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() + OrderDB.getOrderTotalCost().get(orderId));
                        cancelPlacementNotification.sendCancelPlacementBySMS(username, userImp.getUser(username).getPhone());
                        return "Order Cancelled";
                    }
                }
                return "Order not Placed";
            }
            return "Order not found";
        }
        return "User not found";
    }

    public String shipOrder(String username, int orderId) {
     //   OrderShipmentTemplate shipmentNotification = new OrderShipmentTemplate();
        for (Order order : orderDB.getOrders()) {
            if (userImp.checkUser(username)) {
                if (orderDB.findOrder(orderId)) {
                    if (order.getPlaced()) {
                        order.setShipped(true);
                        order.setPlacementTime(LocalDateTime.now());
                        userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() - order.getShippingFees());
       //                shipmentNotification.sendOrderShipmentBySMS(username, userImp.getUser(username).getPhone());
                        return "Order Shipped";
                    }
                }
                return "Order not placed";
            }
            return "User not found";
        }
        return "Order not found";
    }

    public String ShipCompoundOrder(int CompoundOrderID){
        //OrderShipmentTemplate shipmentNotification = new OrderShipmentTemplate();
        if(orderDB.findCompoundOrder(CompoundOrderID)){

            for (Order order : orderDB.getCompoundOrders().get(CompoundOrderID)) {
                shipOrder(order.getUser().getUsername(), order.getOrderId());
                order.setCompoundOrderTime(LocalDateTime.now());
            //    shipmentNotification.sendOrderShipmentBySMS(order.getUser().getUsername(), order.getUser().getPhone());
            }
            return "Compound Order Shipped";
        }
        return "Compound Order not found";
    }
    public String CancelOrderCompoundShipment(int CompoundOrderID ) {
        //CancelShipmentTemplate cancelShipmentNotification = new CancelShipmentTemplate();
        if (orderDB.findCompoundOrder(CompoundOrderID)) {
            LocalDateTime currentTime = LocalDateTime.now();

            for (Order order : orderDB.getCompoundOrders().get(CompoundOrderID)) {
                Duration duration = Duration.between(order.getCompoundOrderTime(), currentTime);
                if (duration.toMinutes() <= CANCELLATION_DURATION_LIMIT) {
                    cancelOrderShipping(order.getUser().getUsername(), order.getOrderId());
          //      cancelShipmentNotification.sendCancelShipmentBySMS(order.getUser().getUsername(), order.getUser().getPhone());
                } else {
                    return "Shipment Cancellation period has expired";
                }

            }
            return "Compound Order Shipping Cancelled";
        }
        return "Compound Order not found";
    }

    public String cancelOrderShipping(String username, int orderId) {
        Order order = new Order();
        CancelShipmentTemplate cancelShipmentNotification = new CancelShipmentTemplate();
        if (userImp.checkUser(username)) {
            if (order.getShipped()) {
                if (orderDB.findOrder(orderId)) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    LocalDateTime orderShipmentTime = order.getShipmentTime();
                    Duration duration = Duration.between(orderShipmentTime, currentTime);
                    if (duration.toMinutes() <= CANCELLATION_DURATION_LIMIT) {
                        order.setShipped(false);
                        userImp.getUser(username).setBalance(userImp.getUser(username).getBalance() + order.getShippingFees());
                        cancelShipmentNotification.sendCancelShipmentBySMS(username, userImp.getUser(username).getPhone());
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


    public ArrayList<String> getOrder(int orderId, String username) {
    //    Order order = new Order();
//        if (!order.getStatus()) {
//            return null;
//        }
        ArrayList<String> orderDetails = orderDB.getOrderMap().get(orderId);
        if (orderDetails != null) {
            return orderDetails;
        }
        orderDetails = new ArrayList<>();
        if (orderDB.findOrder(orderId)) {
            for (Product product : orderDB.getOrderedProducts().get(orderId)) {
                String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                orderDetails.add(orderProduct);
            }
            orderDB.getOrderMap().put(orderId, orderDetails);
            return orderDetails;
        }
        return null;
    }

    public ArrayList<String> getOrders() {
        ArrayList<String> orders = new ArrayList<>();
        String orderProduct = "";
        for (Order order : OrderDB.getOrdersMap().values()){
            orderProduct = "";
            for (Product product : orderDB.getOrderedProducts().get(order.getOrderId())) {
                 orderProduct+= "Product Details: " + product.getName() + " x "
                         + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity()) + "\n";
             }
            String orderDetails = "Order Details: OrderID: " + order.getOrderId() + " Products: " + orderProduct;

            orders.add(orderDetails);
        }
        return orders;
    }

    public ArrayList<String> getCompoundOrder(int CompoundOrderID){
        ArrayList<String> orders = new ArrayList<>();
        String orderProduct = "";
        for (Order order : OrderDB.getCompoundOrders().get(CompoundOrderID)){
            orderProduct = "";
            for (Product product : orderDB.getOrderedProducts().get(order.getOrderId())) {
                orderProduct+= "Product Details: " + product.getName() + " x "
                        + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity()) + "\n";
            }
            String orderDetails = "Order Details: OrderID: " + order.getOrderId() + " Products: " + orderProduct;

            orders.add(orderDetails);
        }
        return orders;
    }



    public String checkOut(String username,int orderId) {
        if (!userImp.checkUser(username)) {
            return "User not found";
        }

        for (Order order : OrderDB.getOrdersMap().values()) {
                    String cost = String.valueOf(orderDB.getOrderTotalCost().get(orderId) + order.getShippingFees());
                  //  NotificationsQueue.popQueue();
                    return "Total Cost: " + "$" + cost +"    " + order.getOrderId();
         }



        return "Order not placed yet";
    }

    public String CompoundOrderCheckout(int CompoundOrderID){

    //loop on compoud orders until you get match of id
    //get the total cost of the order
//return the total cost
        if (orderDB.findCompoundOrder(CompoundOrderID)) {
                String cost = String.valueOf(orderDB.getCompoundOrderTotalCosts().get(CompoundOrderID));
         //   NotificationsQueue.popQueue();
            return "Total Cost: " + "$" + cost +"    " + CompoundOrderID;
        }
        return "Compound Order not found";
    }
}

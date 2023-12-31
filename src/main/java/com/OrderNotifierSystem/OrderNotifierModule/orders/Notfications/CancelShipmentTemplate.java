package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

public class CancelShipmentTemplate extends NotificationTemplate{

public String cancelShipmentMessage(String username) {
    User user = usersBSL.getUser(username);
    notificationTemplate.setSubject("Order Shipment cancellation");
    notificationTemplate.setContent("Dear " + username + ",\n" +
            "Your order of the item(s) "  + user.getOrders()  +
            " has it's shipment cancelled \n" +
            "Your balance is " + user.getBalance() + ".\n" +
            "Thank you for shopping with us.");
    return notificationTemplate.getContent();
}
    public String sendByEmail(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "Sending Notification to email: " + email + "/n" + cancelShipmentMessage(username);
        }
        return "Email does not match";
    }
    public String sendBySMS(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            return "Sending Notification to phone: " + phone + "/n" + cancelShipmentMessage(username);
        }
        return "Phone does not match";
    }
}

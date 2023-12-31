package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

public class OrderShipmentTemplate extends NotificationTemplate{

    public String orderShipmentMessage(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("Order Shipment");
        notificationTemplate.setContent("Dear " + username + ",\n" +
                "Your order of the item(s) "  + user.getOrders()  +
                " has been shipped.\n" +
                "Your balance is " + user.getBalance() + ".\n" +
                "Thank you for shopping with us.");
        return notificationTemplate.getContent();
    }
    public String sendOrderShipmentByEmail(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "Sending Notification to email: " + email + "/n" + orderShipmentMessage(username);
        }
        return "Email does not match";
    }
    public String sendOrderShipmentBySMS(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            return "Sending Notification to phone: " + phone + "/n" + orderShipmentMessage(username);
        }
        return "Phone does not match";
    }

    public String orderShipmentMessageArabic(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("تأكيد شحن الطلب");
        notificationTemplate.setContent("عزيزي " + username + ",\n" +
                "تم شحن طلبك.\n" +
                "رصيدك هو " + user.getBalance() + ".\n" +
                "شكرا لتسوقك معنا.");
        return notificationTemplate.getContent();
    }

    public String sendOrderShipmentByEmailArabic(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "تم ارسال بريد الكتروني الى: " + email + "/n" + orderShipmentMessageArabic(username);
        }
        return "الايميل غير متطابق";
    }
}
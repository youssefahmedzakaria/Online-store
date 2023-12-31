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
    public String sendcancelShipmentByEmail(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "Sending Notification to email: " + email + "/n" + cancelShipmentMessage(username);
        }
        return "Email does not match";
    }
    public String sendcancelShipmentBySMS(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            return "Sending Notification to phone: " + phone + "/n" + cancelShipmentMessage(username);
        }
        return "Phone does not match";
    }

    public String cancelShipmentMessageArabic(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("تأكيد الغاء شحن الطلب");
        notificationTemplate.setContent("عزيزي " + username + ",\n" +
                "تم الغاء شحن طلبك.\n" +
                "رصيدك هو " + user.getBalance() + ".\n" +
                "شكرا لتسوقك معنا.");
        return notificationTemplate.getContent();
    }

    public String sendcancelShipmentByEmailArabic(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "تم ارسال بريد الكتروني الى: " + email + "/n" + cancelShipmentMessageArabic(username);
        }
        return "الايميل غير متطابق";
    }
}

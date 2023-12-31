package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

public class CancelShipmentTemplate extends NotificationTemplate{

    public String CancelShip= "CancelShipment";
public String cancelShipmentMessage(String username) {
    User user = usersBSL.getUser(username);
    notificationTemplate.setSubject("Order Shipment cancellation");
    notificationTemplate.setContent("Dear " + username + ",\n" +
            "Your order of the item(s) "  + user.getOrders()  +
            " has it's shipment cancelled \n" +
            "Your balance is " + user.getBalance() + ".\n" +
            "Thank you for shopping with us.");
    notificationMap.put(CancelShip,notificationMap.get(CancelShip)+1);
    NotificationsQueue.addNotification(notificationTemplate.getContent());
    return notificationTemplate.getContent();
}
    public String sendCancelShipmentByEmail(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            notificationMap.put(CancelShip,notificationMap.get(CancelShip)+1);
            NotificationsQueue.addNotification(notificationTemplate.getContent());
            return "Sending Notification to email: " + email + "/n" + cancelShipmentMessage(username);
        }
        return "Email does not match";
    }
    public String sendCancelShipmentBySMS(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            notificationMap.put(CancelShip,notificationMap.get(CancelShip)+1);
            NotificationsQueue.addNotification(notificationTemplate.getContent());
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
        notificationMap.put(CancelShip,notificationMap.get(CancelShip)+1);
        NotificationsQueue.addNotification(notificationTemplate.getContent());
        return notificationTemplate.getContent();
    }

    public String sendCancelShipmentByEmailArabic(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            notificationMap.put(CancelShip,notificationMap.get(CancelShip)+1);
            NotificationsQueue.addNotification(notificationTemplate.getContent());
            return "تم ارسال بريد الكتروني الى: " + email + "/n" + cancelShipmentMessageArabic(username);
        }
        return "الايميل غير متطابق";
    }

    public String sendCancelShipmentBySMSArabic(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            notificationMap.put(CancelShip,notificationMap.get(CancelShip)+1);
            NotificationsQueue.addNotification(notificationTemplate.getContent());
            return "تم ارسال رسالة نصية الى: " + phone + "/n" + cancelShipmentMessageArabic(username);
        }
        return "رقم الهاتف غير متطابق";
    }
}

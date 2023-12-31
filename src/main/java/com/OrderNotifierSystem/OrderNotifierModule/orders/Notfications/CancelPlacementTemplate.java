package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

public class CancelPlacementTemplate extends NotificationTemplate {
    public String CancelPlacementTemplate(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("Order Placement Cancellation");
        notificationTemplate.setContent("Dear " + username + ",\n" +
                "Your order of the item(s) " + user.getOrders() +
                " has been cancelled.\n" +
                "Your balance is " + user.getBalance() + ".\n" +
                "Thank you for shopping with us.");
        return notificationTemplate.getContent();
    }

    public String sendCancelPlacementByEmail(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "Sending Notification to email: " + email + "/n" + CancelPlacementTemplate(username);
        }
        return "Email does not match";
    }

    public String sendCancelPlacementBySMS(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            return "Sending Notification to phone: " + phone + "/n" + CancelPlacementTemplate(username);
        }
        return "Phone does not match";
    }

    public String CancelPlacementTemplateArabic(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("تأكيد الغاء الطلب");
        notificationTemplate.setContent("عزيزي " + username + ",\n" +
                "تم الغاء طلبك.\n" +
                "رصيدك هو " + user.getBalance() + ".\n" +
                "شكرا لتسوقك معنا.");
        return notificationTemplate.getContent();
    }

    public String sendCancelPlacementByEmailArabic(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "تم ارسال بريد الكتروني الى: " + email + "/n" + CancelPlacementTemplateArabic(username);
        }
        return "الايميل غير متطابق";
    }

    public String sendCancelPlacementBySMSArabic(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            return "تم ارسال رسالة نصية الى: " + phone + "/n" + CancelPlacementTemplateArabic(username);
        }
        return "رقم الهاتف غير متطابق";
    }


}

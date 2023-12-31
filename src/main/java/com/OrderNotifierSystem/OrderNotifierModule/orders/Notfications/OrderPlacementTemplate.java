package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

public class OrderPlacementTemplate extends NotificationTemplate {

    public String orderPlacementMessage(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("Order Placement");
        notificationTemplate.setContent("Dear " + username + ",\n" +
                "Your order of the item(s) "  + user.getOrders() +
                " has been placed.\n" +
                "Your balance is " + user.getBalance() + ".\n" +
                "Thank you for shopping with us.");
        return notificationTemplate.getContent();
    }

    public String sendOrderPlacementByEmail(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "Sending Notification to email: " + email + "/n" + orderPlacementMessage(username);
        }
        return "Email does not match";
    }
    public String sendOrderPlacementBySMS(String username, String phone){
        User user = usersBSL.getUser(username);
        if(user.getPhone().equals(phone)){
            return "Sending Notification to phone: " + phone + "/n" + orderPlacementMessage(username);
        }
        return "Phone does not match";
    }
    //give the user the option to choose the language

    public String orderPlacementMessageArabic(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("تأكيد طلب");
        notificationTemplate.setContent("عزيزي " + username + ",\n" +
                "تم تأكيد طلبك.\n" +
                "رصيدك هو " + user.getBalance() + ".\n" +
                "شكرا لتسوقك معنا.");
        return notificationTemplate.getContent();
    }

    public String sendOrderPlacementByEmailArabic(String username, String email){
        User user = usersBSL.getUser(username);
        if(user.getEmail().equals(email)){
            return "تم ارسال بريد الكتروني الى: " + email + "/n" + orderPlacementMessageArabic(username);
        }
        return "الايميل غير متطابق";
    }
}

package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

public class OrderPlacementTemplate extends NotificationTemplate {

    public String OrderPlaceMent = "OrderPlacement";

    public String orderPlacementMessage(String username) {
        User user = usersBSL.getUser(username);
        notificationTemplate.setSubject("Order Placement");
        notificationTemplate.setContent("Dear " + username + ",\n" +
                "Your order of the item(s) " + user.getOrders() +
                " has been placed.\n" +
                "Your balance is " + user.getBalance() + ".\n" +
                "Thank you for shopping with us.");
        if (notificationMap.get(OrderPlaceMent) == null) {
            notificationMap.put(OrderPlaceMent, 1);
        } else {
            notificationMap.put(OrderPlaceMent, notificationMap.get(OrderPlaceMent) + 1);

        }
        NotificationsQueue.getNotificationQueue().add(notificationTemplate.getContent());
        return notificationTemplate.getContent();
    }

    public String sendOrderPlacementByEmail(String username, String email) {
        for (User user1 : usersBSL.getUsers()) {
            if (user1.getUsername().equals(username)) {
                if (user1.getEmail().equals(email)) {
                    if (notificationMap.get(OrderPlaceMent) == null) {
                        notificationMap.put(OrderPlaceMent, 1);
                    } else {
                        notificationMap.put(OrderPlaceMent, notificationMap.get(OrderPlaceMent) + 1);

                    }
                    NotificationsQueue.getNotificationQueue().add(notificationTemplate.getContent());
                    return "Sending Notification to email: " + email + "/n" + orderPlacementMessage(username);
                }
                return "Email does not match";
            }
        }
        return "User not found";
    }

    public String sendOrderPlacementBySMS(String username, String phone) {
        User user = usersBSL.getUser(username);
        if (user.getPhone().equals(phone)) {
            if (notificationMap.get(OrderPlaceMent) == null) {
                notificationMap.put(OrderPlaceMent, 1);
            } else {
                notificationMap.put(OrderPlaceMent, notificationMap.get(OrderPlaceMent) + 1);

            }
            NotificationsQueue.getNotificationQueue().add(notificationTemplate.getContent());
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
        if (notificationMap.get(OrderPlaceMent) == null) {
            notificationMap.put(OrderPlaceMent, 1);
        } else {
            notificationMap.put(OrderPlaceMent, notificationMap.get(OrderPlaceMent) + 1);

        }
        NotificationsQueue.getNotificationQueue().add(notificationTemplate.getContent());
        return notificationTemplate.getContent();
    }

    public String sendOrderPlacementByEmailArabic(String username, String email) {
        for (User user : usersBSL.getUsers()) {
            if (user.getUsername().equals(username)) {
                if (user.getEmail().equals(email)) {
                    if (notificationMap.get(OrderPlaceMent) == null) {
                        notificationMap.put(OrderPlaceMent, 1);
                    } else {
                        notificationMap.put(OrderPlaceMent, notificationMap.get(OrderPlaceMent) + 1);

                    }
                    NotificationsQueue.getNotificationQueue().add(notificationTemplate.getContent());
                    return "تم ارسال بريد الكتروني الى: " + email + "/n" + orderPlacementMessageArabic(username);
                }
                return "الايميل غير متطابق";
            }
        }
        return "المستخدم غير موجود";
    }

    public String sendOrderPlacementBySMSArabic(String username, String phone) {
        User user = usersBSL.getUser(username);
        if (user.getPhone().equals(phone)) {
            if (notificationMap.get(OrderPlaceMent) == null) {
                notificationMap.put(OrderPlaceMent, 1);
            } else {
                notificationMap.put(OrderPlaceMent, notificationMap.get(OrderPlaceMent) + 1);

            }
            NotificationsQueue.getNotificationQueue().add(notificationTemplate.getContent());
            return "تم ارسال رسالة نصية الى: " + phone + "/n" + orderPlacementMessageArabic(username);
        }
        return "رقم الهاتف غير متطابق";
    }
}

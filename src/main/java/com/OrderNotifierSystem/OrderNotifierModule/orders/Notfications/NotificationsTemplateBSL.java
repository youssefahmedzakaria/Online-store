//package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;
//
//import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
//import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.UserDB;
//import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
//import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;
//
//public class NotificationsTemplateBSL
//{
//    protected static NotificationTemplate notificationTemplate = new NotificationTemplate();
//    protected static UsersImp usersBSL = new UsersImp();
//    protected static OrderDB orderDB = new OrderDB();
//    protected static UserDB userDB = new UserDB();
//
//    public  String orderPlacementMessage(String username) {
//        User user = usersBSL.getUser(username);
//        notificationTemplate.setSubject("Order Placement");
//        notificationTemplate.setContent("Dear " + username + ",\n" +
//                "Your order of the item(s) "  + userDB.getUserOrders().get(username)  +
//                " has been placed.\n" +
//                "Your balance is " + user.getBalance() + ".\n" +
//                "Thank you for shopping with us.");
//        return notificationTemplate.getContent();
//    }
//
//    public  String orderShipmentMessage(String username) {
//        User user = usersBSL.getUser(username);
//        notificationTemplate.setSubject("Order Shipment");
//        notificationTemplate.setContent("Dear " + username + ",\n" +
//                "Your order of the item(s) " + userDB.getUserOrders().get(username) +  "has been shipped.\n" +
//                "It will be delivered to " + user.getAddress() + ".\n" +
//                "Thank you for shopping with us.");
//        return notificationTemplate.getContent();
////        } else {
////            return "عزيزي " + username + ",\n" +
////                    "تم تأكيد طلبك.\n" +
////                    "سيتم توصيل طلبك إلى " + address + ".\n" +
////                    "رصيدك هو " + balance + ".\n" +
////                    "شكرا لتسوقك معنا.";
//    }
//
//    public  String orderCancellationMessage(String username) {
//        User user = usersBSL.getUser(username);
//        notificationTemplate.setSubject("Order Cancellation");
//        notificationTemplate.setContent("Dear " + username + ",\n" +
//                "Your order of the item(s) " + userDB.getUserOrders().get(username) + " has been cancelled.\n" +
//                "Your balance is " + user.getBalance() + ".\n" +
//                "Thank you for shopping with us.");
//        return notificationTemplate.getContent();
//    }
//}
//}

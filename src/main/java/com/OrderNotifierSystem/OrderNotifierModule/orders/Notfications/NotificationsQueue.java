package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;

import java.util.Queue;

public class NotificationsQueue {

    OrderDB orderDB = new OrderDB();
    private static Queue<NotificationTemplate> notificationQueue = new java.util.LinkedList<>();

    public static Queue<NotificationTemplate> getNotificationQueue() {
        return notificationQueue;
    }


    public static void addNotification(NotificationTemplate notificationTemplate){
        notificationQueue.add(notificationTemplate);
    }

    public static NotificationTemplate getNotification(){
        return notificationQueue.poll();
    }


}

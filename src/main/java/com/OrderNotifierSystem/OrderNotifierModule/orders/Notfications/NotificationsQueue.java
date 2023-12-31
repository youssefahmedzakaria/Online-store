package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;

import java.util.Queue;

public class NotificationsQueue {

    private static Queue<String> notificationQueue = new java.util.LinkedList<>();

    public static Queue<String> getNotificationQueue() {
        return notificationQueue;
    }


    public static void addNotification(String notificationTemplate){
        notificationQueue.add(notificationTemplate);
    }

    public static String getNotification(){
        return notificationQueue.poll();
    }
    public static void popQueue(){
        if(notificationQueue.isEmpty())
            return;
        notificationQueue.poll();
    }


}

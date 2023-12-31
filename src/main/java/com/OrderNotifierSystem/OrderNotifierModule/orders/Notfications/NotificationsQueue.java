package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import lombok.NoArgsConstructor;

import java.util.Queue;

@NoArgsConstructor
public class NotificationsQueue {

    private static Queue<String> notificationQueue = new java.util.LinkedList<>();


    public static Queue<String> getNotificationQueue() {
        return notificationQueue;
    }





    public static void addNotification(String notificationTemplate){
        notificationQueue.add(notificationTemplate);
    }

    public static Queue<String> getNotification(){
        return notificationQueue;
    }


    public static void popQueue(){
        if(notificationQueue.isEmpty())
            return;
        notificationQueue.poll();
    }


}

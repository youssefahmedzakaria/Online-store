package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import java.util.Queue;

public class NotificationsQueue {
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

package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.UserDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;

import java.util.HashMap;
import java.util.Map;

public class NotificationTemplate {
   public static Map<String,Integer> notificationMap = new HashMap<String,Integer>();
    protected static NotificationTemplate notificationTemplate = new NotificationTemplate();

    public static UsersImp getUsersBSL() {
        return usersBSL;
    }

    protected static UsersImp usersBSL = new UsersImp();
    protected static OrderDB orderDB = new OrderDB();
    protected static UserDB userDB = new UserDB();
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
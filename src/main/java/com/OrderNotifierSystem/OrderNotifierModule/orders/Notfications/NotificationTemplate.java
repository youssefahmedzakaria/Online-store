package com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications;

import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.UserDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;

public class NotificationTemplate {
    protected static NotificationTemplate notificationTemplate = new NotificationTemplate();
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
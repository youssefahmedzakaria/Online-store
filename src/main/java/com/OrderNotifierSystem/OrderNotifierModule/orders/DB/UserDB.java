package com.OrderNotifierSystem.OrderNotifierModule.orders.DB;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;

import java.util.*;

public class UserDB {
    private static final ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }


}

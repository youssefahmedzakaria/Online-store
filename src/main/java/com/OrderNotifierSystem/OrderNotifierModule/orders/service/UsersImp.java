package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.UserDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersImp {
    private static UserDB users = new UserDB();

    public String viewUser(String username) {
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(username)) {
                return "username: " + user.getUsername() + "\n" +
                        "email: " + user.getEmail() + "\n" +
                        "password: " + user.getPassword() + "\n" +
                        "address: " + user.getAddress() + "\n" +
                        "balance: " + user.getBalance() + "\n" +
                        "phone: " + user.getPhone() + "\n";
            }
                continue;
        }
        return "User not found";
    }


    public void createUser(User user) {
        users.getUsers().add(user);
    }

    public List<User> getUsers() {
        return users.getUsers();
    }

    public User getUser(String username) {
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public boolean checkUser(String username) {
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

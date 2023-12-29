package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.UserDB;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersImp {
    private final UserDB users = new UserDB();



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


    public void addBalance(String username, float balance) {
        for (User user : users.getUsers()) {
            if (user.getUsername().equals(username)) {
                user.setBalance(user.getBalance() + balance);
            }
        }
    }

}

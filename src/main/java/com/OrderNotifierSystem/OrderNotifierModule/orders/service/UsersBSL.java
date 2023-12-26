package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersBSL {
    private final List<User> users ;

    @Autowired
    public UsersBSL(List<User> users) {
        this.users = users;
    }


    public void createUser(User user) {
        user.setBalance();
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

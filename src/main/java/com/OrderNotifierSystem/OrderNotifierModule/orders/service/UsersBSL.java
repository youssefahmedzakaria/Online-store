package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersBSL {
    private final List<Users> users ;

    @Autowired
    public UsersBSL(List<Users> users) {
        this.users = users;
    }


    public void createUser(Users user) {
        user.setBalance();
        users.add(user);
    }

    public List<Users> getUsers() {
        return users;
    }

    public Users getUser(String username) {
        for (Users user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkUser(String username) {
        for (Users user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

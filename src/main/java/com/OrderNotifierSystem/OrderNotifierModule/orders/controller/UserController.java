package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Response;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersBSL;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UsersBSL usersBSL;

    public UserController(UsersBSL usersBSL) {
        this.usersBSL = usersBSL;
    }

    @PostMapping("/add")
    public Response addPerson(@RequestBody User p) {
        System.out.println("in add person"+p);
        boolean flag = usersBSL.checkUser(p.getUsername());
        if (flag) {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("User Already Exists");
            return response;
        }
        usersBSL.createUser(p);
        Response response = new Response();
        response.setStatus(true);
        response.setMessage("User created successfully");
        return response;
    }

    @GetMapping("/get/{username}")
    public User getPerson(@PathVariable("username") String username) {
        boolean flag;
        System.out.println("in get with username:" + username);

        flag = usersBSL.checkUser(username);
        if (!flag) {
            System.out.println("User Doesn't Exist");
            return null;
        }
        return usersBSL.getUser(username);
    }

    @GetMapping("/get")
    public List<User> get() {
        return usersBSL.getUsers();
    }


    @GetMapping("/habal")
    public String habal() {
        return "Habal";
    }
}

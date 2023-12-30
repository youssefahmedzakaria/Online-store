package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class SimpleOrder extends Order {

    public Order order = new Order();

    public OrderDB orderDB = new OrderDB();
    public UsersImp userImp = new UsersImp();

    private static final int CANCELLATION_DURATION_LIMIT = 2;
    //create empty cons

}


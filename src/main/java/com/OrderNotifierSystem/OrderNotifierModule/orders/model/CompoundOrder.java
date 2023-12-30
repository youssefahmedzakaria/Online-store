package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CompoundOrder extends Order{

    private ArrayList<SimpleOrder> simpleOrders = new ArrayList<>();

    public void addSimpleOrder(SimpleOrder simpleOrder) {
        simpleOrders.add(simpleOrder);
    }

    public void removeSimpleOrder(SimpleOrder order) {
        simpleOrders.remove(order);
    }

}
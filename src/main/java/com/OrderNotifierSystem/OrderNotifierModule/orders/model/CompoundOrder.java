package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.SimpleOrderImp;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompoundOrder {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0); // ID generator for unique compound order IDs
    private int compoundOrderId;
    private static Map<Integer, Float> individualTotalCosts;
    private static float totalCompoundCost;
    private static float totalShippingCost;
    private static boolean isCompoundOrderPlaced;
    private static boolean isCompoundOrderShipped;
    //create a map of CompoundOrders based on its id
    private static Map<Integer, CompoundOrder> compoundOrderMap = new HashMap<>();

    private static boolean isCompoundOrderCancelled;

    private static LocalDateTime ShipmentTime;

    private static ArrayList<SimpleOrderImp> simpleOrders;

    private static final int CANCELLATION_DURATION_LIMIT = 2;
    public CompoundOrder() {
        this.compoundOrderId = ID_GENERATOR.incrementAndGet(); // Assign a unique ID
        this.simpleOrders = new ArrayList<>();
        this.individualTotalCosts = new HashMap<>();
        this.totalCompoundCost = 0;
        this.totalShippingCost = 0;
        this.isCompoundOrderPlaced = false;
        this.isCompoundOrderShipped = false;
        this.isCompoundOrderCancelled = false;
        //place it in the map
        compoundOrderMap.put(compoundOrderId, this);
    }

    public String PlaceOrder(Integer compoundOrderId) {

        if(!isCompoundOrderPlaced){
            isCompoundOrderPlaced = true;
            return "Compound Order Placed";
        }
        else{
            return "Compound Order already placed";
        }
    }

    public String addSimpleOrder(int simpleOrderId, int compoundOrderId) {
        if(getCompoundOrder(compoundOrderId)){
            if (!isCompoundOrderPlaced) {
                SimpleOrderImp order = findSimpleOrderById(simpleOrderId); // Method to find SimpleOrderImp by ID
                if (order == null) {
                    return "Simple Order not found";
                }
                simpleOrders.add(order);
                float cost = OrderDB.getOrderTotalCost().get(simpleOrderId);
                individualTotalCosts.put(simpleOrderId, cost);
                totalCompoundCost += cost;
                return "Simple Order added to Compound Order";
            } else {
                return "Compound Order already placed";
            }
           }
        else {
            return "Compound Order not found";
        }
    }

    public String removeSimpleOrder(int simpleOrderId, int compoundOrderId) {
        if(getCompoundOrder(compoundOrderId)){

            if (!isCompoundOrderPlaced) {
                SimpleOrderImp order = findSimpleOrderById(simpleOrderId);
                if (order == null) {
                    return "Simple Order not found";
                }
                simpleOrders.remove(order);
                float cost = OrderDB.getOrderTotalCost().get(simpleOrderId);
                individualTotalCosts.remove(simpleOrderId, cost);
                totalCompoundCost -= cost;
                return "Simple Order removed from Compound Order";
            } else {
                return "Compound Order already placed";
            }
        }
        else {
            return "Compound Order not found";
        }
    }
        private SimpleOrderImp findSimpleOrderById(int orderId) {
        // Implement logic to retrieve SimpleOrderImp from OrderDB or elsewhere
        // Placeholder implementation
        for (Order order : OrderDB.getOrders()) {
            if (order.getOrderId() == orderId) {
                // Assuming SimpleOrderImp can be instantiated or retrieved like this
                return new SimpleOrderImp(order);
            }
        }
        return null;
    }


    public void cancelCompoundOrder() {
        for (SimpleOrderImp order : simpleOrders) {
            order.cancelOrderPlacement(order.order.getUser().getUsername(),order.order.getOrderId());
        }
        isCompoundOrderCancelled = true;

    }
 public void getCompoundOrderList(){
        //print CompoundOrderID
        System.out.println("Compound Order ID: " + compoundOrderId);
        for (SimpleOrderImp order : simpleOrders) {
            order.getOrder(order.order.getOrderId());
        }
    }

    public boolean getCompoundOrder(int compoundOrderId) {
        if (compoundOrderMap.containsKey(compoundOrderId)) {
            return true;
        }
        return false;
    }

 public String ShipCompoundOrder() {
        //save the local time of when the order was placed
        ShipmentTime = LocalDateTime.now();
        for (SimpleOrderImp order : simpleOrders) {
            order.shipOrder(order.order.getUser().getUsername(),order.order.getOrderId());
            //set the compound order to shipped
            isCompoundOrderShipped = true;
            //add shipping cost to the total shipping cost
            totalShippingCost += order.order.getShippingFees();
        }
        return "CompoundOrder Shipped";
    }
    public String CancelShipment(){
        //get the current time and compare it to the time the order was shipped and check if
        // duration surpases CANCELLATION_DURATION_LIMIT value
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(ShipmentTime, currentTime);
        if(duration.toMinutes() <= CANCELLATION_DURATION_LIMIT){
            for (SimpleOrderImp order : simpleOrders) {
                order.cancelOrderShipping(order.order.getUser().getUsername(),order.order.getOrderId());
            }
            //set the compound order to not shipped
            isCompoundOrderShipped = false;
            return "Shipment Cancelled";
        }
        else{
            return "Cancellation time limit exceeded";
        }

    }

    public int getCompoundOrderId() {
        return compoundOrderId;
    }

    public static Map<Integer, Float> getIndividualTotalCosts() {
        return individualTotalCosts;
    }

    public static float getTotalCompoundCost() {
        return totalCompoundCost;
    }

    public static float getTotalShippingCost() {
        return totalShippingCost;
    }

    public static boolean isIsCompoundOrderPlaced() {
        return isCompoundOrderPlaced;
    }

    public static boolean isIsCompoundOrderShipped() {
        return isCompoundOrderShipped;
    }

    public static boolean isIsCompoundOrderCancelled() {
        return isCompoundOrderCancelled;
    }

    public static LocalDateTime getShipmentTime() {
        return ShipmentTime;
    }

    public static ArrayList<SimpleOrderImp> getSimpleOrders() {
        return simpleOrders;
    }
}

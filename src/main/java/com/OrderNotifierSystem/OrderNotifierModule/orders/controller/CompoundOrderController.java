package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.CompoundOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compoundOrders")
public class CompoundOrderController {

    // Endpoint to create a new Compound Order
    @PostMapping("/create")
    public ResponseEntity<String> createCompoundOrder() {
        CompoundOrder compoundOrder = new CompoundOrder();
        return ResponseEntity.ok("Compound Order created with ID: " + compoundOrder.getCompoundOrderId());
    }

    // Endpoint to add a Simple Order to a Compound Order
    @PostMapping("/add/{compoundOrderId}/{simpleOrderId}")
    public ResponseEntity<String> addSimpleOrderToCompound(@PathVariable int compoundOrderId, @PathVariable int simpleOrderId) {
        CompoundOrder compoundOrder = CompoundOrder.getCompoundOrder(compoundOrderId);
        if (compoundOrder != null) {
            String response = compoundOrder.addSimpleOrder(simpleOrderId, compoundOrderId);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Compound Order not found");
    }

    // Endpoint to remove a Simple Order from a Compound Order
    @DeleteMapping("/remove/{compoundOrderId}/{simpleOrderId}")
    public ResponseEntity<String> removeSimpleOrderFromCompound(@PathVariable int compoundOrderId, @PathVariable int simpleOrderId) {
        CompoundOrder compoundOrder = CompoundOrder.getCompoundOrder(compoundOrderId);
        if (compoundOrder != null) {
            String response = compoundOrder.removeSimpleOrder(simpleOrderId, compoundOrderId);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Compound Order not found");
    }

    // Endpoint to place a Compound Order
    @PostMapping("/place/{compoundOrderId}")
    public ResponseEntity<String> placeCompoundOrder(@PathVariable int compoundOrderId) {
        CompoundOrder compoundOrder = CompoundOrder.getCompoundOrder(compoundOrderId);
        if (compoundOrder != null && !CompoundOrder.isIsCompoundOrderPlaced()) {
            String response = compoundOrder.PlaceOrder(compoundOrderId);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Compound Order not found or already placed");
    }

    // Endpoint to cancel a Compound Order
    @PostMapping("/cancel/{compoundOrderId}")
    public ResponseEntity<String> cancelCompoundOrder(@PathVariable int compoundOrderId) {
        CompoundOrder compoundOrder = CompoundOrder.getCompoundOrder(compoundOrderId);
        if (compoundOrder != null) {
            compoundOrder.cancelCompoundOrder();
            return ResponseEntity.ok("Compound Order cancelled");
        }
        return ResponseEntity.badRequest().body("Compound Order not found");
    }

    // Additional endpoints as required...

}

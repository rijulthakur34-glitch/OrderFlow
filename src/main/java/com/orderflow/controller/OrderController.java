package com.orderflow.controller;

import com.orderflow.model.ExpressOrder;
import com.orderflow.model.StandardOrder;
import com.orderflow.service.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderProcessor orderProcessor;

    @GetMapping("/health")
    public String healthCheck() {
        return "OrderFlow API is running successfully!";
    }

    @PostMapping("/process")
    public String processOrder(@RequestBody OrderRequestDTO request) {
        try {
            if ("EXPRESS".equalsIgnoreCase(request.getType())) {
                ExpressOrder order = new ExpressOrder(request.getOrderId(), request.getValue(), request.getItems());
                return orderProcessor.processSingleOrder(order);
            } else {
                StandardOrder order = new StandardOrder(request.getOrderId(), request.getValue(), request.getItems());
                return orderProcessor.processSingleOrder(order);
            }
        } catch (Exception e) {
            return "Error processing order: " + e.getMessage();
        }
    }
}

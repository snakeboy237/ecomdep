
package com.app.controller;

import com.app.model.Order;
import com.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @PostMapping("/checkout")
    public String checkout(@RequestBody Order order) {
        orderRepo.save(order);
        return "Order placed!";
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderRepo.findAll();
    }
}

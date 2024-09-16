package com.book.store.controller.ordercontroller;

import com.book.store.model.ordermodel.Order;
import com.book.store.service.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/add/order")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        Order order = orderService.placeOrder(userId, productId, quantity);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/admin/get/order/{userId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orders);
    }
}

package com.book.store.controller.ordercontroller;


import com.book.store.dto.orderdto.OrderDTO;
import com.book.store.model.ordermodel.Order;
import com.book.store.service.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add/order")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.placeOrder(orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/order/history/{userId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orders);
    }
}
package com.book.store.controller.ordercontroller;

import com.book.store.dto.orderdto.OrderDTO;
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
    private OrderService orderService;

    @PostMapping("/add/order")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO placedOrder = orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }
}

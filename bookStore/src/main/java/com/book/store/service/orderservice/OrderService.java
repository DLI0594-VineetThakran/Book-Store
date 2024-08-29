package com.book.store.service.orderservice;


import com.book.store.dto.orderdto.OrderDTO;
import com.book.store.model.ordermodel.Order;
import com.book.store.model.ordermodel.OrderItem;
import com.book.store.model.ordermodel.OrderStatus;
import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import com.book.store.repository.orderrepository.OrderItemRepository;
import com.book.store.repository.orderrepository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Order placeOrder(OrderDTO orderDTO) {
        Order order = new Order();

        User user = new User();
        user.setId(orderDTO.getUserId());
        order.setUser(user);

        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderStatus(OrderStatus.valueOf(orderDTO.getOrderStatus()));
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(itemDTO -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            Product product = new Product();
            product.setId(itemDTO.getProductId());
            orderItem.setProduct(product);

            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(itemDTO.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return order;
    }

    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }
}
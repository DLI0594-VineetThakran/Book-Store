package com.book.store.service.orderservice;

import com.book.store.dto.orderdto.OrderDTO;
import com.book.store.dto.orderdto.OrderItemDTO;
import com.book.store.model.ordermodel.Order;
import com.book.store.model.ordermodel.OrderItem;
import com.book.store.model.ordermodel.OrderStatus;
import com.book.store.model.usermodel.User;
import com.book.store.repository.orderrepository.OrderRepository;
import com.book.store.repository.productrepository.ProductRepository;
import com.book.store.repository.userrepository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderDTO placeOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(itemDTO -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productRepository.findById(itemDTO.getProductId()).orElseThrow(() -> new RuntimeException("Product not found")));
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(itemDTO.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        orderDTO.setId(order.getId());
        orderDTO.setOrderStatus(order.getOrderStatus().name());
        orderDTO.setCreatedAt(order.getCreatedAt());
        return orderDTO;
    }

    public List<OrderDTO> getOrderHistory(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setUserId(order.getUser().getId());
            orderDTO.setTotalAmount(order.getTotalAmount());
            orderDTO.setOrderStatus(order.getOrderStatus().name());
            orderDTO.setCreatedAt(order.getCreatedAt());
            orderDTO.setOrderItems(order.getOrderItems().stream().map(item -> {
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setId(item.getId());
                itemDTO.setOrderId(item.getOrder().getId());
                itemDTO.setProductId(item.getProduct().getId());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPrice(item.getPrice());
                return itemDTO;
            }).collect(Collectors.toList()));
            return orderDTO;
        }).collect(Collectors.toList());
    }
}
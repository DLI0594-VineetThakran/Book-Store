package com.book.store.service.orderservice;

import com.book.store.model.cartmodel.Cart;
import com.book.store.model.ordermodel.Order;
import com.book.store.model.ordermodel.OrderItem;
import com.book.store.model.ordermodel.OrderStatus;
import com.book.store.model.usermodel.User;
import com.book.store.repository.cartrepository.CartItemRepository;
import com.book.store.repository.cartrepository.CartRepository;
import com.book.store.repository.orderrepository.OrderItemRepository;
import com.book.store.repository.orderrepository.OrderRepository;
import com.book.store.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInterface{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Order placeOrder(Long userId ) {
        User user=userRepository.getById(userId);
        System.out.println("user "+user);
        Cart cart = cartRepository.findByUserId(userId);
        System.out.println("cart "+cart);
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        // Clear the cart
        cartItemRepository.deleteAll(cart.getItems());

        return order;
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}

package com.book.store.service.orderservice;

import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import com.book.store.model.ordermodel.Order;
import com.book.store.model.ordermodel.OrderItem;
import com.book.store.model.ordermodel.OrderStatus;
import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import com.book.store.repository.cartrepository.CartItemRepository;
import com.book.store.repository.cartrepository.CartRepository;
import com.book.store.repository.orderrepository.OrderItemRepository;
import com.book.store.repository.orderrepository.OrderRepository;
import com.book.store.repository.productrepository.ProductRepository;
import com.book.store.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Order placeOrder(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId).orElse(null);
        System.out.println("user " + user);
        Product product = productRepository.findById(productId).orElse(null);
        System.out.println("product " + product);

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity))); // Updated line

        // Reduce stock from product
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        order.setItems(Collections.singletonList(orderItem));
        orderRepository.save(order);
        orderItemRepository.save(orderItem);

        // Clear the cart
        cartItemRepository.delete(cartItem);

        return order;
    }




    @Override
    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}

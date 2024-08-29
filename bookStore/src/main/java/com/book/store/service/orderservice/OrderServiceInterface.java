package com.book.store.service.orderservice;

import com.book.store.model.ordermodel.Order;

import java.util.List;

public interface OrderServiceInterface {
    //public Order placeOrder(Long userId, String paymentDetails, String shippingDetails);
    public List<Order> getOrderHistory(Long userId);
    public Order placeOrder(Long userId );
}

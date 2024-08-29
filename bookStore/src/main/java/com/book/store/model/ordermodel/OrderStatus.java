package com.book.store.model.ordermodel;

public enum OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    @Override
    public String toString() {
        return "OrderStatus{}";
    }
}
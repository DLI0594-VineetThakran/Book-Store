package com.book.store.repository.orderrepository;

import com.book.store.model.ordermodel.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
package com.book.store.repository.orderrepository;

import com.book.store.model.ordermodel.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

package com.book.store.repository.cartrepository;

import com.book.store.model.cartmodel.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void delete(CartItem cartItem);
}

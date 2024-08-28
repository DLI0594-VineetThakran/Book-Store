package com.book.store.repository.cartrepository;

import com.book.store.model.cartmodel.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c where c.user.id =: userId")
    Optional<Cart> findByUserId(@Param("userId") Long userId);
}


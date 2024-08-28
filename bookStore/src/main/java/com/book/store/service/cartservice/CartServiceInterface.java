package com.book.store.service.cartservice;

import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartServiceInterface {
//    Cart createCart(Long userId);
    ResponseEntity<String> addCartItem(Long productId, Long userid);
    CartItem updateCartItemQuantity(Long cartItemId, Integer quantity);
    void removeCartItem(Long cartItemId);
    List<CartItem> getCartItems();
}


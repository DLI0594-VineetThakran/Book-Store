package com.book.store.service.cartservice;

import com.book.store.dto.cartdto.CartDTO;

public interface CartServiceInterface {
    CartDTO addCartItem(Long productId, Long userId);
    CartDTO updateCartItemQuantity(Long cartItemId, Integer quantity);
    void removeCartItem(Long cartItemId);
    CartDTO getCartItems(Long userId);
}


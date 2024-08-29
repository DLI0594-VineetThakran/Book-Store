package com.book.store.service.cartService;

import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;

import java.util.List;

public interface CartServiceInterface {
    public Cart addProductToCart(Long userId, Long productId, Integer quantity);
    public Cart updateCartItemQuantity(Long cartItemId, Integer quantity);

    public void removeProductFromCart(Long cartItemId);
    public List<CartItem> getAllCartItems(Long userId);
}

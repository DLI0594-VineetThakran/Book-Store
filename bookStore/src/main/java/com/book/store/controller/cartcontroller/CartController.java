package com.book.store.controller.cartcontroller;

import com.book.store.dto.cartdto.CartDTO;
import com.book.store.service.cartservice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add_cart_item/{product_id}")
    public ResponseEntity<CartDTO> addCartItem(@PathVariable Long productId, @RequestParam Long userId) {
        CartDTO cartDTO = cartService.addCartItem(productId, userId);
        return ResponseEntity.ok(cartDTO);
    }

    @PutMapping("/cart_item_quantity/{cartItem_id}")
    public ResponseEntity<CartDTO> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam Integer quantity) {
        CartDTO cartDTO = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/remove_cart_item/{cartItem_id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get_cart_items")
    public ResponseEntity<CartDTO> getCartItems(@RequestParam Long userId) {
        CartDTO cartDTO = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartDTO);
    }
}


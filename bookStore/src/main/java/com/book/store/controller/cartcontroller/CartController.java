package com.book.store.controller.cartcontroller;

import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import com.book.store.service.cartservice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bookstore_user")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add_cart_item/{product_id}")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long userId, @PathVariable Long product_id, @RequestParam Integer quantity) {
        Cart cart = cartService.addProductToCart(userId, product_id, quantity);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update_cart_item/{cartItemId}")
    public ResponseEntity<Cart> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam Integer quantity) {
        Cart cart = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public String removeProductFromCart(@PathVariable Long cartItemId) {
        return cartService.removeProductFromCart(cartItemId);
    }

    @GetMapping("/{userId}/cart-items")
    public ResponseEntity<List<CartItem>> getAllCartItems(@PathVariable Long userId) {
        List<CartItem> items = cartService.getAllCartItems(userId);
        return ResponseEntity.ok(items);
    }
}

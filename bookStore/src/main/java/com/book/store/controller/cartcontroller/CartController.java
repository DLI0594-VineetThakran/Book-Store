package com.book.store.controller.cartcontroller;

import com.book.store.dto.cartdto.CartDto;
import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import com.book.store.service.cartservice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/create_cart")
    public Cart createCart(@RequestParam Long userId){
        return cartService.createCart(userId);
    }

    @PostMapping("/add_cart_item/{product_id}")
    public CartItem addItem(@RequestBody CartDto cartDto){
        Long cartId = cartDto.getCartId();
        Long productID = cartDto.getProductId();
        return cartService.addCartItem(cartId, productID);
    }

    @PutMapping("/cart_item_quantity/{cartItem_id}")
    public CartItem updateItemQuantity(@PathVariable("cartItem_id") Long cartItemId, @RequestBody Integer quantity){
        return cartService.updateCartItemQuantity(cartItemId, quantity);
    }

    @DeleteMapping("/remove_cart_item/{cartItem_id}")
    public void removeItem(@PathVariable("cartItem_id") Long cartItemId){
        cartService.removeCartItem(cartItemId);
    }

    @GetMapping("/get_cart_items")
    public List<CartItem> getAllItems(){
        return cartService.getCartItems();
    }

}


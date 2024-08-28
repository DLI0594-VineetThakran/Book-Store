package com.book.store.service.cartservice;


import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import com.book.store.model.productmodel.Product;
import com.book.store.repository.cartrepository.CartItemRepository;
import com.book.store.repository.cartrepository.CartRepository;
import com.book.store.repository.productrepository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceInterface {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    @Override
    public ResponseEntity<String> addCartItem(Long productId, Long userid) {
        Optional<Cart> cart=cartRepository.findByUserId(userid);
        Product productOpt = productRepository.findById(productId).orElse(null);
        if (productOpt==null) {
            return ResponseEntity.badRequest().body("Product not found");
        }

        Product product = productRepository.findById(productId).get();
        System.out.println(product);
        if (product.getStock() <= 0) {
            return ResponseEntity.badRequest().body("Product is out of stock");
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setProductId(product.getId());
        cartItem.setCart(cart.get());
        cartItemRepository.save(cartItem);


        return ResponseEntity.ok("Product added to cart");
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }


}




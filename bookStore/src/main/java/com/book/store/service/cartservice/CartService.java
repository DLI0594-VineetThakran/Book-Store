package com.book.store.service.cartservice;

import com.book.store.dto.cartdto.CartDTO;
import com.book.store.dto.cartdto.CartItemDTO;
import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import com.book.store.repository.cartrepository.CartItemRepository;
import com.book.store.repository.cartrepository.CartRepository;
import com.book.store.repository.productrepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartDTO addCartItem(Long productId, Long userId) {
        // Fetch the cart for the user
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(new User(userId)); // Assuming User has a constructor with userId
            newCart.setCreatedAt(LocalDateTime.now());
            return cartRepository.save(newCart);
        });

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product is already in the cart
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    return newItem;
                });

        // Update the quantity
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);

        // Convert to DTO and return
        return convertToCartDTO(cart);
    }

    public CartDTO updateCartItemQuantity(Long cartItemId, Integer quantity) {
        // Fetch the cart item
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        // Update the quantity
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        // Convert to DTO and return
        return convertToCartDTO(cartItem.getCart());
    }

    public void removeCartItem(Long cartItemId) {
        // Fetch the cart item
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        // Remove the item from the cart
        cartItemRepository.delete(cartItem);
    }

    public CartDTO getCartItems(Long userId) {
        // Fetch the cart for the user
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Convert to DTO and return
        return convertToCartDTO(cart);
    }

    private CartDTO convertToCartDTO(Cart cart) {
        List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream()
                .map(item -> new CartItemDTO(item.getId(), item.getProduct().getId(), item.getQuantity()))
                .collect(Collectors.toList());

        return new CartDTO(cart.getId(), cart.getUser().getId(), cart.getCreatedAt(), cartItemDTOs);
    }
}



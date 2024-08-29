package com.book.store.service.cartservice;

import com.book.store.exception.ResourceNotFoundException;
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

@Service
public class CartService implements CartServiceInterface{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addProductToCart(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(new User(userId));
            cart.setCreatedAt(LocalDateTime.now());
            cart = cartRepository.save(cart);
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart updateCartItemQuantity(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return cartItem.getCart();
    }

    @Override
    public String removeProductFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return "Item Deleted!";
    }

    @Override
    public List<CartItem> getAllCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            return cart.getItems();
        }
        throw new ResourceNotFoundException("Cart not found for user id: " + userId);
    }
}

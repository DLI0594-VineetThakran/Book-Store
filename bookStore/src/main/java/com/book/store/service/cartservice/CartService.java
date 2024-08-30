package com.book.store.service.cartservice;

import com.book.store.exception.ResourceNotFoundException;
import com.book.store.exception.UserAlreadyExistsException;
import com.book.store.model.cartmodel.Cart;
import com.book.store.model.cartmodel.CartItem;
import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import com.book.store.repository.cartrepository.CartItemRepository;
import com.book.store.repository.cartrepository.CartRepository;
import com.book.store.repository.productrepository.ProductRepository;
import com.book.store.repository.userrepository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart addProductToCart(Long userId, Long productId, Integer quantity) {

        // Fetch the user from the database
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        // Fetch the cart associated with the user
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user); // Set the managed User entity
            cart.setCreatedAt(LocalDateTime.now());
            cart = cartRepository.save(cart);
        }

        // Fetch the product from the database
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        // Create a new CartItem and associate it with the cart and product
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        // Save the cart with the new item
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
        if (!cartItemRepository.existsById(cartItemId)) {
            return "Item not found!";
        }

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

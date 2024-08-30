package com.book.store.service.wishlistservice;

import com.book.store.exception.ResourceNotFoundException;
import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import com.book.store.model.wishlistmodel.Wishlist;
import com.book.store.model.wishlistmodel.WishlistItem;
import com.book.store.repository.productrepository.ProductRepository;
import com.book.store.repository.userrepository.UserRepository;
import com.book.store.repository.wishlistrepository.WishlistItemRepository;
import com.book.store.repository.wishlistrepository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class WishlistService implements WishlistServiceInterface{
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Wishlist addItemToWishlist(Long userId, Long productId) {
        // Fetch the user from the database
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Fetch the wishlist for the user
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(user); // Set the fetched user
            wishlist.setCreatedAt(LocalDateTime.now());
            wishlist = wishlistRepository.save(wishlist);
        }

        // Fetch the product from the database
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Create and add the wishlist item
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setProduct(product);
        wishlist.getItems().add(wishlistItem);

        // Save the wishlist
        wishlistRepository.save(wishlist);
        return wishlist;
    }


    @Transactional
    public String removeProductFromWishlist(Long wishlistItemId) {
        // Check if the item exists
        if (!wishlistItemRepository.existsById(wishlistItemId)) {
            throw new ResourceNotFoundException("Wishlist item not found");
        }

        // Delete the item
        wishlistItemRepository.deleteById(wishlistItemId);

        return "Item Deleted!";
    }

    public List<WishlistItem> getAllWishlistItems(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist != null) {
            return wishlist.getItems();
        }
        throw new ResourceNotFoundException("Wishlist not found for user id: " + userId);
    }
}

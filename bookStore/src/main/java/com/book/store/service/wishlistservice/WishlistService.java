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

    @Transactional
    public Wishlist addItemToWishlist(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        User user = new User();
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(new User(userId));
            wishlist.setCreatedAt(LocalDateTime.now());
            wishlist = wishlistRepository.save(wishlist);
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setProduct(product);
        wishlist.getItems().add(wishlistItem);

        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public String removeProductFromWishlist(Long wishlistItemId) {
        System.out.println("Item Deleted!");
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

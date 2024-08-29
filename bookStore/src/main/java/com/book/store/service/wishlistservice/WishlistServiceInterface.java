package com.book.store.service.wishlistservice;

import com.book.store.model.productmodel.Product;
import com.book.store.model.wishlistmodel.Wishlist;
import com.book.store.model.wishlistmodel.WishlistItem;

import java.util.List;
import java.util.Set;

public interface WishlistServiceInterface {
    public Wishlist addItemToWishlist(Long userId, Long productId);
    public void removeProductFromWishlist(Long wishlistItemId);
    public List<WishlistItem> getAllWishlistItems(Long userId);

}

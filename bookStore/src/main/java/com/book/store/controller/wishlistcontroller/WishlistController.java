package com.book.store.controller.wishlistcontroller;

import com.book.store.model.wishlistmodel.Wishlist;
import com.book.store.model.wishlistmodel.WishlistItem;
import com.book.store.service.wishlistservice.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @PostMapping("/add_wish_list_item/{product_id}")
    public ResponseEntity<Wishlist> addProductToWishlist(@RequestParam Long userId, @PathVariable Long product_id) {
        Wishlist wishlist = wishlistService.addItemToWishlist(userId, product_id);
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/wishlist/remove/{wishlistItemId}")
    public String removeProductFromWishlist(@PathVariable Long wishlistItemId) {
        return wishlistService.removeProductFromWishlist(wishlistItemId);

    }

    @GetMapping("/{userId}/wishlist-items")
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems(@PathVariable Long userId) {
        List<WishlistItem> items = wishlistService.getAllWishlistItems(userId);
        return ResponseEntity.ok(items);
    }

}

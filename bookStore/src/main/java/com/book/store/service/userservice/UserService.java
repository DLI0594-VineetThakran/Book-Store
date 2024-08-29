package com.book.store.service.userservice;

import com.book.store.dto.userdto.LoginDTO;
import com.book.store.jwtutil.userjwtutil.UserJwtUtil;
import com.book.store.model.cartmodel.Cart;
import com.book.store.model.usermodel.User;
import com.book.store.model.wishlistmodel.Wishlist;
import com.book.store.repository.cartrepository.CartRepository;
import com.book.store.repository.userrepository.UserRepository;
import com.book.store.repository.wishlistrepository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserJwtUtil userJwtUtil;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User registeredUser = userRepository.save(user);

        // Create a wishlist for the new user
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(registeredUser);
        wishlist.setCreatedAt(LocalDateTime.now());
        wishlistRepository.save(wishlist);

        // Create a cart for the new user
        Cart cart = new Cart();
        cart.setUser(registeredUser);
        cart.setCreatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        // Set the wishlist and cart to the user
        registeredUser.setWishlist(wishlist);
        registeredUser.setCart(cart);
        userRepository.save(registeredUser);


        return registeredUser;
    }

    @Override
    public String loginUser(LoginDTO loginDTO) {
         User  user =  userRepository.findByUsername( loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        if (passwordEncoder.matches( loginDTO.getPassword(),  user.getPassword())) {
            return  userJwtUtil.generateToken( user.getUsername());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }


public boolean verifyToken(String token) {
    String username = userJwtUtil.extractUsername(token);
    Optional<User> userOptional = userRepository.findByUsername(username);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        return userJwtUtil.validateToken(token, userDetails);
    }
    return false;
}

}

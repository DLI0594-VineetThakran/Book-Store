package com.book.store.model.usermodel;

import com.book.store.model.cartmodel.Cart;
import com.book.store.model.wishlistmodel.Wishlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

<<<<<<< HEAD
    @Column(columnDefinition = "TEXT")
    private String address;

    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;
=======
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wishlist wishlist;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    public User(Long userId) {
    }
>>>>>>> 350153b7f75ff75a8c7ce656a697cf11436c76b1
}

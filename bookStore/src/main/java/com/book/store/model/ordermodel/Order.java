package com.book.store.model.ordermodel;

import com.book.store.model.usermodel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

    @Entity
    @Table(name = "Orders")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column(nullable = false)
        private BigDecimal totalAmount;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private OrderStatus orderStatus;

        @Column(nullable = false)
        private LocalDateTime createdAt;

        @OneToMany(mappedBy = "order")
        private List<OrderItem> orderItems;

    }





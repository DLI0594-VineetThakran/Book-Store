package com.book.store.model.customerdetailsmodel;

import com.book.store.model.usermodel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CustomerDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String phoneNumber;

    public CustomerDetails(String phoneNumber, String address, String email, String name, User user) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.name = name;
        this.user = user;
    }
}

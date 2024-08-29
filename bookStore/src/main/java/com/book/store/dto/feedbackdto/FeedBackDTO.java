package com.book.store.dto.feedbackdto;


import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackDTO {
    private Long id;
    private User user;
    private Product product;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}

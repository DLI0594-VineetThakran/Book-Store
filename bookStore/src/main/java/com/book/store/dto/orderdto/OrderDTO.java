package com.book.store.dto.orderdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private String orderStatus;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> orderItems;
}


package com.book.store.dto.orderdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;
    private BigDecimal totalAmount;
    private String orderStatus;
}
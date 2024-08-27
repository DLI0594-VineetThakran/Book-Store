package com.book.store.dto.admindto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String userName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}

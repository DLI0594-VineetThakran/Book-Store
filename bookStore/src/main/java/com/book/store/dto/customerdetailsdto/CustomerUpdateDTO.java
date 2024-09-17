package com.book.store.dto.customerdetailsdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}

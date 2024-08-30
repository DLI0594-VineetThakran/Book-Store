package com.book.store.controller.customerdetailscontroller;

import com.book.store.dto.customerdetailsdto.CustomerDetailsDTO;
import com.book.store.dto.customerdetailsdto.CustomerUpdateDTO;
import com.book.store.service.customerdetailsservice.CustomerDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @PostMapping("/add_customer")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDetailsDTO customerDetailsDTO){
        customerDetailsService.addCustomer(customerDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully!");
    }
    @PutMapping("/edit_user")
    public ResponseEntity<?> updateCustomerDetails(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        customerDetailsService.updateCustomerDetails(customerUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer edited successfully!");
    }
}
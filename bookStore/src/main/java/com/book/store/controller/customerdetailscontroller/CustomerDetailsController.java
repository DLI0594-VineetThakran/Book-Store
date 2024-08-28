package com.book.store.controller.customerdetailscontroller;

import com.book.store.dto.customerdetailsdto.CustomerDetailsDTO;
import com.book.store.service.customerdetailsservice.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore_user")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @PutMapping("/edit_user")
    public ResponseEntity<CustomerDetailsDTO> updateCustomerDetails(@RequestBody CustomerDetailsDTO customerDetailsDTO) {
        CustomerDetailsDTO updatedCustomerDetails = customerDetailsService.updateCustomerDetails(customerDetailsDTO);
        return ResponseEntity.ok(updatedCustomerDetails);
    }
}
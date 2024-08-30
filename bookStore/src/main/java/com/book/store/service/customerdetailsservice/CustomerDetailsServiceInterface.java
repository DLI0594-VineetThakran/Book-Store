package com.book.store.service.customerdetailsservice;

import com.book.store.dto.customerdetailsdto.CustomerDetailsDTO;
import com.book.store.dto.customerdetailsdto.CustomerUpdateDTO;

public interface CustomerDetailsServiceInterface {
    void addCustomer(CustomerDetailsDTO customerDetailsDTO);
    void updateCustomerDetails(CustomerUpdateDTO customerUpdateDTO);
}


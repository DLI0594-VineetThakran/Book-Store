package com.book.store.service.customerdetailsservice;

import com.book.store.dto.customerdetailsdto.CustomerDetailsDTO;
import com.book.store.model.customerdetailsmodel.CustomerDetails;
import com.book.store.model.usermodel.User;
import com.book.store.repository.customerdetailsrepository.CustomerDetailsRepository;
import com.book.store.repository.userrepository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CustomerDetailsDTO updateCustomerDetails(CustomerDetailsDTO customerDetailsDTO) {
        CustomerDetails customerDetails = customerDetailsRepository.findById(customerDetailsDTO.getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        User user = userRepository.findById(customerDetailsDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        customerDetails.setUser(user);
        customerDetails.setName(customerDetailsDTO.getName());
        customerDetails.setEmail(customerDetailsDTO.getEmail());
        customerDetails.setAddress(customerDetailsDTO.getAddress());
        customerDetails.setPhoneNumber(customerDetailsDTO.getPhoneNumber());

        customerDetailsRepository.save(customerDetails);

        return customerDetailsDTO;
    }
}
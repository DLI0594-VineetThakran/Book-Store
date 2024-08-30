package com.book.store.service.customerdetailsservice;

import com.book.store.dto.customerdetailsdto.CustomerDetailsDTO;
import com.book.store.dto.customerdetailsdto.CustomerUpdateDTO;
import com.book.store.model.customerdetailsmodel.CustomerDetails;
import com.book.store.model.usermodel.User;
import com.book.store.repository.customerdetailsrepository.CustomerDetailsRepository;
import com.book.store.repository.userrepository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements CustomerDetailsServiceInterface {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addCustomer(CustomerDetailsDTO customerDetailsDTO){
        User user=userRepository.getById(customerDetailsDTO.getUserId());
        CustomerDetails customerDetails=new CustomerDetails(customerDetailsDTO.getPhoneNumber(),customerDetailsDTO.getAddress(), customerDetailsDTO.getEmail(), customerDetailsDTO.getName(),user
        );
        System.out.println(customerDetails);
        customerDetailsRepository.save(customerDetails);
    }

    @Override
    @Transactional
    public void updateCustomerDetails(CustomerUpdateDTO customerUpdateDTO) {
        CustomerDetails customerDetails=customerDetailsRepository.getById(customerUpdateDTO.getId());
        customerDetails.setName(customerUpdateDTO.getName());
        customerDetails.setAddress(customerUpdateDTO.getAddress());
        customerDetails.setEmail(customerUpdateDTO.getEmail());

        customerDetailsRepository.save(customerDetails);
    }
}
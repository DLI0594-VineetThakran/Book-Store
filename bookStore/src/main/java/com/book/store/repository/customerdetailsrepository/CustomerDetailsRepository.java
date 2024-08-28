package com.book.store.repository.customerdetailsrepository;

import com.book.store.model.customerdetailsmodel.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

}
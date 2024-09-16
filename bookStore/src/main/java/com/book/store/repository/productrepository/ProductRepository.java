package com.book.store.repository.productrepository;

import com.book.store.model.productmodel.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product ,Long> {
//    @Query("SELECT p FROM Product p WHERE p.deleted = false")
//    List<Product> findAllActiveProducts();
}

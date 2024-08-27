//package com.book.store.service.product;
//
//import com.book.store.model.product.Product;
//import com.book.store.repository.product.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//    public Product Add(Product product){
//        return productRepository.save(product);
//    }
//    public Product updateEmployee(Long id,Product productdetails){
//        Product product=productRepository.findById(id).orElseThrow() - > new ResourceNotFoundException("Employee not found for this id :" + id) }
//}

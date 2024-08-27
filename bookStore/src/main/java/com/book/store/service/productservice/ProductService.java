package com.book.store.service.productservice;

import com.book.store.model.productmodel.Product;
import com.book.store.repository.productrepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
  private  ProductRepository productRepository;
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product updateProduct(Long id, Product product){
        product.setId(id);
        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}

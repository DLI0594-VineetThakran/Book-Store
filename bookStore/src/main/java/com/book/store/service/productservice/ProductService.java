package com.book.store.service.productservice;

import com.book.store.exception.ResourceNotFoundException;
import com.book.store.model.productmodel.Product;
import com.book.store.repository.productrepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setDeleted(false);
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product existing = existingProduct.get();

            // Retain the original createdAt value
            product.setId(id);
            product.setCreatedAt(existing.getCreatedAt());
            product.setUpdatedAt(LocalDateTime.now());

            // Update feedbacks collection
            if (product.getFeedbacks() != null) {
                existing.getFeedbacks().clear();
                existing.getFeedbacks().addAll(product.getFeedbacks());
            } else {
                existing.getFeedbacks().clear();
            }

            // Update other fields as needed
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setStock(product.getStock());
            existing.setAuthor(product.getAuthor());
            existing.setCategory(product.getCategory());

            return productRepository.save(existing);
        } else {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setDeleted(true);
            productRepository.save(existingProduct);
            return "Item marked as deleted";
        } else {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }
}

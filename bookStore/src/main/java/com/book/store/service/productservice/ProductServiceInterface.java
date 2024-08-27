package com.book.store.service.productservice;

import com.book.store.model.productmodel.Product;

public interface ProductServiceInterface {
    public Product addProduct(Product product);
    public Iterable<Product> getAllProducts();
    public Product updateProduct(Long id, Product product);
    public void deleteProduct(Long id);
}

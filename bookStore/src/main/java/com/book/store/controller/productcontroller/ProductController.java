package com.book.store.controller.productcontroller;

import com.book.store.model.productmodel.Product;
import com.book.store.service.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/add/book")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @GetMapping("/get/book")
    public Iterable<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PutMapping("/admin/update/book/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }
    @DeleteMapping("/admin/delete/book/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}

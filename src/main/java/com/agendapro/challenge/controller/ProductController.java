package com.agendapro.challenge.controller;

import com.agendapro.challenge.domain.Product;
import com.agendapro.challenge.exception.ProductNotFoundException;
import com.agendapro.challenge.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.update(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products")
    public List<Product> listAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product listProductById(@PathVariable long id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @GetMapping("/search-by-name/{name}")
    public List<Product> searchProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }
}

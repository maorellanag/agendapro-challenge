package com.agendapro.challenge.controller;

import com.agendapro.challenge.domain.Product;
import com.agendapro.challenge.exception.ProductNotFoundException;
import com.agendapro.challenge.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Crear un producto")
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @Operation(summary = "Actualizar un producto")
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.update(id, product);
    }

    @Operation(summary = "Eliminar un producto por Id")
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) throws ProductNotFoundException {
        productService.deleteById(id);
    }

    @Operation(summary = "Obtener todos los productos")
    @GetMapping("/products")
    public List<Product> listAllProducts() {
        return productService.findAll();
    }

    @Operation(summary = "Obtener un producto por Id")
    @GetMapping("/products/{id}")
    public Product listProductById(@PathVariable long id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @Operation(summary = "Buscar productos por nombre")
    @GetMapping("/search-by-name/{name}")
    public List<Product> searchProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @Operation(summary = "Obtener estadísticas de uso")
    @GetMapping("/statistics")
    public HashMap<String, Integer> getStatistics() {
        return productService.getStatistics();
    }
}

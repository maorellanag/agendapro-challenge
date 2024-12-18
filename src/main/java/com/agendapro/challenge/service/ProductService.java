package com.agendapro.challenge.service;

import com.agendapro.challenge.domain.Product;
import com.agendapro.challenge.exception.ProductNotFoundException;
import com.agendapro.challenge.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StatisticsService statisticsService;

    public ProductService(ProductRepository productRepository, StatisticsService statisticsService) {
        this.productRepository = productRepository;
        this.statisticsService = statisticsService;
    }

    public Product findById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ProductNotFoundException("Product not found using id = " + id);

        return product.get();
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        productRepository.save(product);
        statisticsService.increaseCounter(product.getCategory());
        return product;
    }

    public void deleteById(long id) throws ProductNotFoundException {

        Product productFound = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        productRepository.deleteById(id);
        statisticsService.decreaseCounter(productFound.getCategory());
    }

    public Product update(long id, Product updatedProduct) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        if (isDifferentCategory(existingProduct, updatedProduct)) {
            statisticsService.decreaseCounter(existingProduct.getCategory());
            statisticsService.increaseCounter(updatedProduct.getCategory());
        }

        BeanUtils.copyProperties(updatedProduct, existingProduct, "id");

        return productRepository.save(existingProduct);
    }

    public HashMap<String, Integer> getStatistics() {
        return statisticsService.getCategoryCount();
    }

    private boolean isDifferentCategory(Product existingProduct, Product updatedProduct) {
        if (existingProduct.getCategory() != null && updatedProduct.getCategory() != null) {
            return !existingProduct.getCategory().equals(updatedProduct.getCategory());
        }
        return true;
    }
}

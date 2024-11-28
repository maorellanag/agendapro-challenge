package com.agendapro.challenge.service;

import com.agendapro.challenge.domain.Product;
import com.agendapro.challenge.exception.ProductNotFoundException;
import com.agendapro.challenge.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findById_whenProductFound_thenProductFound() throws ProductNotFoundException {
        Product product = Product.builder().id(1L).build();
        when(productRepository.findById(1L))
            .thenReturn(Optional.of(product));

        Product productFound = productService.findById(1);

        assertNotNull(product);
        assertEquals(1L, productFound.getId());
    }

    @Test
    void findById_whenProductNotFound_thenProductNotFoundException() {
        when(productRepository.findById(1L))
            .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findById(1L));
    }

    @Test
    void findByName_whenProductFound_thenProductFound() {
        Product product = Product.builder().id(1L).build();
        when(productRepository.findByName("name1"))
            .thenReturn(List.of(product));

        List<Product> products = productService.findByName("name1");

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1L, products.get(0).getId());
    }

    @Test
    void findByName_whenProductNotFound_thenEmptyList() {
        when(productRepository.findByName("name1"))
            .thenReturn(List.of());

        List<Product> products = productService.findByName("name1");

        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    @Test
    void findAll_thenReturnListOfProducts() {
        Product product1 = Product.builder().name("product1").id(1L).build();
        Product product2 = Product.builder().name("product2").id(2L).build();
        Product product3 = Product.builder().name("product3").id(3L).build();
        List<Product> products = List.of(product1, product2, product3);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productsFound = productService.findAll();

        assertNotNull(productsFound);
        assertEquals(3, productsFound.size());
        assertEquals("product1", productsFound.get(0).getName());
        assertEquals("product2", productsFound.get(1).getName());
        assertEquals("product3", productsFound.get(2).getName());
    }

    @Test
    void save_thenReturnProduct() {
        Product product = Product.builder().name("test").id(1L).build();
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals("test", savedProduct.getName());
    }

    @Test
    void update_whenProductExists_thenReturnUpdatedProduct() throws ProductNotFoundException {
        Product oldProduct = Product.builder().name("old").id(1L).build();
        Product newProduct = Product.builder().name("new").id(2L).build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(oldProduct));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Product updatedProduct = productService.update(1, newProduct);

        assertNotNull(updatedProduct);
        assertEquals("new", updatedProduct.getName());
    }

    @Test
    void update_whenProductNotExist_thenProductNotFoundException() {
        Product newProduct = Product.builder().name("new").id(2L).build();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.update(1, newProduct));
    }

    @Test
    void delete_whenProductExists_deleteOperationCalled() throws ProductNotFoundException {
        Product product = Product.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteById(1);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void delete_whenProductNotExist_thenProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteById(1));
    }

}
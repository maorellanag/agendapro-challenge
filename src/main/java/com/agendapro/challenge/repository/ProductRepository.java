package com.agendapro.challenge.repository;

import com.agendapro.challenge.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByName(String name);
}

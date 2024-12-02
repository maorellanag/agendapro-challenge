package com.agendapro.challenge.repository;

import com.agendapro.challenge.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    @Query(value = "SELECT * FROM product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<Product> findByName(@Param("name") String name);

}

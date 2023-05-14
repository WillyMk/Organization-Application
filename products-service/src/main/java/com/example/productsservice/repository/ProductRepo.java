package com.example.productsservice.repository;

import com.example.productsservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("select s from Product s where s.name = ?1")
    Product findByName (String name);
    Product findByNameContaining (String name);

    Product findByPrice (String price);

}

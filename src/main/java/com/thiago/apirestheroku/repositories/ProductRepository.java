package com.thiago.apirestheroku.repositories;

import com.thiago.apirestheroku.entities.Category;
import com.thiago.apirestheroku.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

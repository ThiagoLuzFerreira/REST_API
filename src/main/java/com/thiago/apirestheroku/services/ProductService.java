package com.thiago.apirestheroku.services;

import com.thiago.apirestheroku.entities.Product;
import com.thiago.apirestheroku.entities.User;
import com.thiago.apirestheroku.repositories.ProductRepository;
import com.thiago.apirestheroku.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = productRepository.findById(id);
        return obj.get();
    }
}

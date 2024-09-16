package com.mobileapp.mobileapp.service;

import com.mobileapp.mobileapp.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void delete(Long id);
}

package com.mobileapp.mobileapp.controller;

import com.mobileapp.mobileapp.dto.ProductResponse;
import com.mobileapp.mobileapp.entity.Product;
import com.mobileapp.mobileapp.service.ProductService;
import com.mobileapp.mobileapp.util.ProductDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<Product> products = productService.findAll();
        List<ProductResponse> response = ProductDtoConversion.convertProductList(products);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody Product product){
        Product savedProduct=productService.save(product);
        ProductResponse response=new ProductResponse(savedProduct.getId(), savedProduct.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductId(@PathVariable Long id){
        Product product=productService.findById(id);
        ProductResponse response=new ProductResponse(product.getId(), product.getName());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

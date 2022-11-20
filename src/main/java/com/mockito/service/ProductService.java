package com.mockito.service;

import com.mockito.entity.Product;
import com.mockito.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
       product.setId(UUID.randomUUID().toString());
       productNameCannotBeBlank(product.getProductName());
       return productRepository.save(product);
    }

    public boolean productNameCannotBeBlank(String name){
        if(name.isEmpty()){
            throw new IllegalStateException("Product Name Cannot Be empty");
        }if (name.length() < 7){
            throw new IllegalStateException("Product Name cannot less than 10");
        }
        return true;
    }
}

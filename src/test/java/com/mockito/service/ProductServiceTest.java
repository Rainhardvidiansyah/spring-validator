package com.mockito.service;

import com.mockito.entity.Product;
import com.mockito.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct() { //PASSED
        var product = new Product();
        product.setProductName("Product Name");
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
        var savedProduct = productService.createProduct(product);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertNotNull(savedProduct.getProductName());
        Mockito.verify(productRepository, Mockito.times(1)).save(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void productNameCannotBeBlank() { //PASSED
        Assertions.assertThrows(IllegalStateException.class, () -> productService
                .productNameCannotBeBlank(""));
    }
}
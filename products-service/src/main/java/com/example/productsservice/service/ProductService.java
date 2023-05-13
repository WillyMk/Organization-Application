package com.example.productsservice.service;

import com.example.productsservice.dto.ProductDto;
import com.example.productsservice.entity.Product;
import com.example.productsservice.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;

    public ProductDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Product p = productRepo.save(product);
        return Product.toDto(p);
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(product -> Product.toDto(product)).collect(Collectors.toList());
    }

    public ProductDto getProductByName(String name) {
        System.out.println("getProductByName " + name);
        Product product = productRepo.findProductByName(name);
        if(product == null){
            throw new RuntimeException("Product not found");
        }
        return Product.toDto(product);
    }
}

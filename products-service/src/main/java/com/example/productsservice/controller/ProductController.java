package com.example.productsservice.controller;

import com.example.productsservice.dto.ProductDto;
import com.example.productsservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto product = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getProducts(){
        List<ProductDto> products = productService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping
    public ResponseEntity<ProductDto> getProduct(@RequestParam(value = "name", required = false) String name){
        ProductDto product = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}

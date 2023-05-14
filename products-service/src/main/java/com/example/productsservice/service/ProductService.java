package com.example.productsservice.service;

import com.example.productsservice.dto.ProductDto;
import com.example.productsservice.entity.Product;
import com.example.productsservice.repository.ProductRepo;
import com.example.productsservice.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;

    public ProductDto saveProduct(ProductDto productDto) {
        Product pd = productRepo.findByNameContaining(productDto.getName());
        if(pd != null) {
            throw new RuntimeException("Item already exists");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Product p = productRepo.save(product);
        return Product.toDto(p);
    }

    public PaginationData getProducts(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize,  Sort.by("price"));
        Page<Product> products = productRepo.findAll(pageable);
        List<Product> productList = products.getContent();
        List ProductDto = productList.stream().map(product -> Product.toDto(product)).collect(Collectors.toList());

        PaginationData content = new PaginationData();
        content.setContent(productList);
        content.setPageNo(products.getNumber() + 1);
        content.setPageSize(products.getSize());
        content.setTotalElements(products.getTotalElements());
        return content;
    }

    public ProductDto getProductByName(String name) {
        Product product = productRepo.findByNameContaining(name);
        if(product == null){
            throw new RuntimeException("Product not found");
        }
        return Product.toDto(product);
    }
}

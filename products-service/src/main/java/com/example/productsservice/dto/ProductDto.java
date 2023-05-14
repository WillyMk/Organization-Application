package com.example.productsservice.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String price;
    private String description;
    private byte image;
}

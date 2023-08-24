package com.example.jpa1.product.model;

import lombok.Data;

@Data
public class ProductRegDto {
    private String name;
    private int price;
    private int stock;
    private long providerId;
    private String description;
}

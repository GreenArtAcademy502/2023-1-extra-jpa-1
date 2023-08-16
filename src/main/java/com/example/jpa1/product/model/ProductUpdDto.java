package com.example.jpa1.product.model;

import lombok.Data;

@Data
public class ProductUpdDto {
    private long number;
    private String name;
    private int price;
    private int stock;
}

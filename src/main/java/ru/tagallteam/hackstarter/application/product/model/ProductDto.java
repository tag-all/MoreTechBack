package ru.tagallteam.hackstarter.application.product.model;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private Long price;

    private Long purchased;

    private Long numberOfProducts;

    private String type;

    private Long fileId;
}

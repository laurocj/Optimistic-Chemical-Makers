package com.optimisticchemicalmakers.mapfood.dtos;

import java.math.BigDecimal;

public class ProductDto {

    // Properties

    private Long id;

    private String description;

    private BigDecimal price;

    // Constructor

    public ProductDto() {
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

}

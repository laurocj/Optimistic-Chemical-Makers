package com.optimisticchemicalmakers.mapfood.factories;

import org.springframework.stereotype.Component;

import com.optimisticchemicalmakers.mapfood.dtos.ProductDto;
import com.optimisticchemicalmakers.mapfood.models.Product;

@Component
public class ProductFactory {

    public ProductDto getInstance(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    public Product getInstance(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return product;
    }
}

package com.optimisticchemicalmakers.mapfood.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.dtos.ProductDto;
import com.optimisticchemicalmakers.mapfood.factories.ProductFactory;
import com.optimisticchemicalmakers.mapfood.services.ProductService;

//Enable Cross-Origin Resource Sharing
@RestController
@RequestMapping(value = "/v1")
public class ProductController {

	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

	@Autowired
	private ProductService productService;
	
	// -----------------------------------------------------------------------------------------------------------------
    // Factories
    // -----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private ProductFactory productFactory;
	
    // -----------------------------------------------------------------------------------------------------------------
	// GET /v1/products/{hash_store}
    // Retorna os produtos de um restaurante
    // -----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/products/{hash_store}")
    public List<ProductDto> getProductByStore(@PathVariable String hash_store) {
        return productService
        		.getByStoreHash(hash_store)
        		.stream()
                .map(productFactory::getInstance)
                .collect(Collectors.toList());
    }
}

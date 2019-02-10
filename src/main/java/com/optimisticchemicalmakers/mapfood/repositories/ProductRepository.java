package com.optimisticchemicalmakers.mapfood.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.optimisticchemicalmakers.mapfood.models.Product;
import com.optimisticchemicalmakers.mapfood.models.Store;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByStore(Store store);

}
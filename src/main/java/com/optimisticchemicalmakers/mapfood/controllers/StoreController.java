package com.optimisticchemicalmakers.mapfood.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.dtos.StoreDto;
import com.optimisticchemicalmakers.mapfood.factories.StoreFactory;
import com.optimisticchemicalmakers.mapfood.services.StoreService;

@RestController
@RequestMapping(value = "/v1")
public class StoreController {
	
	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

	@Autowired
	private StoreService storeService;
	
	// -----------------------------------------------------------------------------------------------------------------
    // Factories
    // -----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private StoreFactory storeFactory;
	
	// -----------------------------------------------------------------------------------------------------------------
    // GET /v1/stores/nearest
    // Busca por Stores disponíveis em uma região
    // -----------------------------------------------------------------------------------------------------------------
	@GetMapping("/stores/nearest")
    public List<StoreDto> getNearestStores(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam("radius") Long radius) {

        return storeService
                .getNearestStores(latitude, longitude, radius)
                .stream()
                .map(store -> storeFactory.getInstance(store, store.distanceTo(latitude, longitude)))
                .collect(Collectors.toList());
    }

}

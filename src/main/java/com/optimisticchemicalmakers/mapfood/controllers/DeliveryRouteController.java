package com.optimisticchemicalmakers.mapfood.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryRouteDto;
import com.optimisticchemicalmakers.mapfood.factories.DeliveryRouteFactory;
import com.optimisticchemicalmakers.mapfood.services.DeliveryRouteService;

@RestController
@RequestMapping(value = "/v1")
public class DeliveryRouteController {
	
	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

//    @Autowired
//    private DeliveryRouteService deliveryRouteService;

    // -----------------------------------------------------------------------------------------------------------------
    // Factories
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private DeliveryRouteFactory deliveryRouteFactory;
    
    // -----------------------------------------------------------------------------------------------------------------
    // GET /v1/routes/{hash_store}
    // Creia uma ordem
    // -----------------------------------------------------------------------------------------------------------------

//    @CrossOrigin(origins = "http://localhost:4200") // temporary for testing in APP angular
//    @GetMapping(value = "/routes/{hash_store}")
//    public List<DeliveryRouteDto> listDeliveryRoute(@PathVariable String hash_store) {
//        return deliveryRouteService
//        		.getDeliveryRouteByStore(hash_store)
//        		.stream()
//        		.map(deliveryRouteFactory::getInstance)
//        		.collect(Collectors.toList());
//    }

}

package com.optimisticchemicalmakers.mapfood.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryOrderDto;
import com.optimisticchemicalmakers.mapfood.factories.DeliveryOrderFactory;
import com.optimisticchemicalmakers.mapfood.services.DeliveryOrderService;

@RestController
@RequestMapping(value = "/v1")
public class DeliveryOrderController {

	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    // -----------------------------------------------------------------------------------------------------------------
    // Factories
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private DeliveryOrderFactory deliveryOrderFactory;
    
    // -----------------------------------------------------------------------------------------------------------------
    // GET /v1/create/delivery-order
    // Creia uma ordem
    // -----------------------------------------------------------------------------------------------------------------

    @CrossOrigin(origins = "http://localhost:4200") // temporary for testing in APP angular
    @PostMapping(value = "/order/create")
    public ResponseEntity<?> createDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrder) {
        return ResponseEntity.ok(
        		deliveryOrderFactory
        		.getInstance(deliveryOrderService
        					.createDeliveryOrder(deliveryOrder)
        					)
        		);
    }

}




package com.optimisticchemicalmakers.mapfood.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryOrderDto;
import com.optimisticchemicalmakers.mapfood.dtos.DeliveryRouteDto;
import com.optimisticchemicalmakers.mapfood.factories.DeliveryOrderFactory;
import com.optimisticchemicalmakers.mapfood.factories.DeliveryRouteFactory;
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

    @Autowired
    private DeliveryRouteFactory deliveryRouteFactory;
    
    // -----------------------------------------------------------------------------------------------------------------
    // GET /v1/order/create
    // Creia uma ordem
    // -----------------------------------------------------------------------------------------------------------------
    @PostMapping(value = "/order/create")
    public ResponseEntity<?> createDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrder) {
        return ResponseEntity.ok(
        		deliveryOrderFactory
        		.getInstance(deliveryOrderService
        					.createDeliveryOrder(deliveryOrder)
        					)
        		);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // POST /v1/order/ready
    // Sinaliza que uma ordem foi finalizada
    // -----------------------------------------------------------------------------------------------------------------
    @PostMapping(value = "/order/ready")
    public ResponseEntity<DeliveryRouteDto> signDeliveryOrderReadyToDeliveryBoy(
            @RequestBody DeliveryOrderDto deliveryOrderDto) {

        return ResponseEntity.ok(deliveryRouteFactory.getInstance(deliveryOrderService.signAsReadyToDelivery(deliveryOrderDto)));

    }

}




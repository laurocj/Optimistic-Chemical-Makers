package com.optimisticchemicalmakers.mapfood.controllers;
import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import com.optimisticchemicalmakers.mapfood.services.DeliveryBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class DeliveryBoyController {

    @Autowired
    DeliveryBoyService deliveryBoyService;

    // GET /deliveryboy/nearest
    // Return the nearest DeliveryBoy
    @GetMapping(value = "/deliveryboys/nearest")
    public List<DeliveryBoy> getDistance(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam("radius") Double radius) {

        return deliveryBoyService.getNearestDeliveryBoys(latitude, longitude, radius);

    }

    // GET /deliveryboy/nearest
    // Return the nearest DeliveryBoy
    @GetMapping(value = "deliveryboy/nearest")
    public ResponseEntity<?> getDistance(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude) {

        return ResponseEntity.ok(deliveryBoyService.getNearestDeliveryBoy(latitude, longitude));

    }




}

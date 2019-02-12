package com.optimisticchemicalmakers.mapfood.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.services.GeolocationService;

@RestController
public class GeolocationController {

	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private GeolocationService geolocationService;

    // -----------------------------------------------------------------------------------------------------------------
    // GET /distance
    // Return the distance between points
    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/distance")
    public ResponseEntity<?> getDistance(

            @RequestParam("sLatitude") double sLatitude,
            @RequestParam("sLongitude") double sLongitude,
            @RequestParam("eLatitude") double eLatitude,
            @RequestParam("eLongitude") double eLongitude

    ) {
        return ResponseEntity.ok(geolocationService.getDistance(sLatitude, sLongitude, eLatitude, eLongitude));
    }

}

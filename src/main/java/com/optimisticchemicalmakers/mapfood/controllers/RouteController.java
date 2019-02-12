package com.optimisticchemicalmakers.mapfood.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.optimisticchemicalmakers.mapfood.services.RouteService;
import com.optimisticchemicalmakers.mapfood.shared.models.RouteRequest;

@RestController("/v1")
public class RouteController {

	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

	@Autowired
	private RouteService routeService;

	// -----------------------------------------------------------------------------------------------------------------
    // POST /v1/route
    // Busca a melhor rota
    // -----------------------------------------------------------------------------------------------------------------
	@PostMapping("/route")
	public ResponseEntity<?> bestRoute(@Valid @RequestBody RouteRequest route) {
		return ResponseEntity.ok(routeService.getBestRoute(route.getStartPoint(), route.getWaypoints()));
	}
}

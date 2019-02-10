package com.optimisticchemicalmakers.mapfood.services;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;

@Service
public class RouteService {
	
	private final GeoApiContext apiContext = new GeoApiContext.Builder()
			.apiKey("AIzaSyBIEwbeXpjsbJXSUnutOZ6p4PqecLwv8EM")
			.build();
	
	public RouteService() {}
	
	public DirectionsResult getBestRoute(LatLng startPoint, List<LatLng> waypoints) {

        LatLng destination = waypoints.get(waypoints.size() - 1);
        waypoints.remove(destination);


        try {
            DirectionsResult directionsResult =  DirectionsApi.newRequest(apiContext)
                    .origin(startPoint)
                    .waypoints(waypoints.toArray(new LatLng[]{}))
                    .destination(destination)
                    .optimizeWaypoints(true)
                    .departureTime(Instant.now())
                    .mode(TravelMode.DRIVING)
                    .trafficModel(TrafficModel.BEST_GUESS)
                    .await();
           /* DirectionsRoute route = directionsResult.routes[0];
            DirectionsLeg leg = route.legs[0];
            Duration duration = leg.duration; */
            return directionsResult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

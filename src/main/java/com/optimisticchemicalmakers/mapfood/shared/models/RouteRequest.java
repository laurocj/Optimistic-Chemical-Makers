package com.optimisticchemicalmakers.mapfood.shared.models;

import java.util.List;

import com.google.maps.model.LatLng;

public class RouteRequest {
	
	private LatLng startPoint;
	private List<LatLng> waypoints;
	
	public LatLng getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(LatLng startPoint) {
		this.startPoint = startPoint;
	}
	public List<LatLng> getWaypoints() {
		return waypoints;
	}
	public void setWaypoints(List<LatLng> waypoints) {
		this.waypoints = waypoints;
	}


}

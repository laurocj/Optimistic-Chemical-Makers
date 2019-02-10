package com.optimisticchemicalmakers.mapfood.services;

import org.springframework.stereotype.Service;

import com.optimisticchemicalmakers.mapfood.models.Geolocation;

@Service
public class GeolocationService {

    public String getDistance(double sLatitude, double sLongitude, double eLatitude, double eLongitude) {

        double distance = Geolocation.haversinDistance(sLatitude, sLongitude, eLatitude, eLongitude);

        return Double.toString(distance);

    }

}
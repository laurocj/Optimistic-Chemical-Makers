package com.optimisticchemicalmakers.mapfood.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeliveryBoy {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    // Constructors
    
    public DeliveryBoy() {}

    public DeliveryBoy(Double latitude, Double longitude) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    // Getters

    public Long getId() {
        return this.id;
    }

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



}


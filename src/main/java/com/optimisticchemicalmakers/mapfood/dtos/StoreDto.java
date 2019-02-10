package com.optimisticchemicalmakers.mapfood.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StoreDto {

	private String hash;

	private Double latitude;

	private Double longitude;

	private String name;

	private String dishDescription;

	private String city;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double requestorDistance;

	public StoreDto() {}

    public String getHash() {
        return this.hash;
	}

	public String getName() {
		return name;
	}

	public String getDishDescription() {
		return dishDescription;
	}

	public String getCity() {
		return city;
	}
    
	public Double getRequestorDistance() {
		return requestorDistance;
	}	

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

    public void setHash(String hash) {
        this.hash = hash;
    }

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDishDescription(String dishDescription) {
		this.dishDescription = dishDescription;
	}

	public void setRequestorDistance(Double requestorDistance) {
		this.requestorDistance = requestorDistance;
	}

	public void setCity(String city) {
		this.city = city;
	}
}

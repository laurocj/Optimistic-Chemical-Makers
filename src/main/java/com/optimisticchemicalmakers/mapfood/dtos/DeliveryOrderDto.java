package com.optimisticchemicalmakers.mapfood.dtos;

import java.util.Date;
import java.util.Set;

public class DeliveryOrderDto {

    private Long id;

    private String storeHash;

    private Double endingLatitude;

    private Double endingLongitude;
    
    private Long customerId;
    
    private Double distance;
    
    private Date estimatedDevliveryTime;

    private Set<DeliveryItemDto> deliveryItems;

    // Constructor

    public DeliveryOrderDto() {}

	public Long getId() {
		return id;
	}

	public String getStoreHash() {
		return storeHash;
	}

	public Double getEndingLatitude() {
		return endingLatitude;
	}

	public Double getEndingLongitude() {
		return endingLongitude;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Date getEstimatedDevliveryTime() {
		return estimatedDevliveryTime;
	}

	public Set<DeliveryItemDto> getDeliveryItems() {
		return deliveryItems;
	}
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStoreHash(String storeHash) {
		this.storeHash = storeHash;
	}

	public void setEndingLatitude(Double endingLatitude) {
		this.endingLatitude = endingLatitude;
	}

	public void setEndingLongitude(Double endingLongitude) {
		this.endingLongitude = endingLongitude;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setEstimatedDevliveryTime(Date estimatedDevliveryTime) {
		this.estimatedDevliveryTime = estimatedDevliveryTime;
	}

	public void setDeliveryItems(Set<DeliveryItemDto> deliveryItems) {
		this.deliveryItems = deliveryItems;
	}
   
}

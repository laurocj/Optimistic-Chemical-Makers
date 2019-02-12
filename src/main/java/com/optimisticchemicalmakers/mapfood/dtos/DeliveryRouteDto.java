package com.optimisticchemicalmakers.mapfood.dtos;

import java.util.Date;
import java.util.List;

public class DeliveryRouteDto {
	
	private List<DeliveryOrderDto> deliveryOrders;

	private String storeHash;
    
    private Long deliveryBoy;

    private Date closedAt;

	public List<DeliveryOrderDto> getDeliveryOrders() {
		return deliveryOrders;
	}

	public String getStoreHash() {
		return storeHash;
	}

	public Long getDeliveryBoy() {
		return deliveryBoy;
	}

	public Date getClosedAt() {
		return closedAt;
	}

	public void setDeliveryOrders(List<DeliveryOrderDto> deliveryOrders) {
		this.deliveryOrders = deliveryOrders;
	}

	public void setStoreHash(String storeHash) {
		this.storeHash = storeHash;
	}

	public void setDeliveryBoy(Long deliveryBoyId) {
		this.deliveryBoy = deliveryBoyId;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
}

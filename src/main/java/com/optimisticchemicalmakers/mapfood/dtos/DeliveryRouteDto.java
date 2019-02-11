package com.optimisticchemicalmakers.mapfood.dtos;

import java.util.Date;
import java.util.List;

public class DeliveryRouteDto {
	
	private List<DeliveryOrderDto> deliveryOrders;

	private String storeHash;
    
    private DeliveryBoyDto deliveryBoy;

    private Date closedAt;

	public List<DeliveryOrderDto> getDeliveryOrders() {
		return deliveryOrders;
	}

	public String getStoreHash() {
		return storeHash;
	}

	public DeliveryBoyDto getDeliveryBoy() {
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

	public void setDeliveryBoy(DeliveryBoyDto deliveryBoy) {
		this.deliveryBoy = deliveryBoy;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
}

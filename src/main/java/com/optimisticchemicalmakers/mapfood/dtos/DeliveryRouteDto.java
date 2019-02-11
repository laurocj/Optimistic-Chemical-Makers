package com.optimisticchemicalmakers.mapfood.dtos;

import java.util.Date;
import java.util.List;

public class DeliveryRouteDto {
	
	private List<DeliveryOrderDto> deliveryOrders;

    private StoreDto store;
    
    private DeliveryBoyDto deliveryBoy;

    private Date closedAt;

	public List<DeliveryOrderDto> getDeliveryOrders() {
		return deliveryOrders;
	}

	public StoreDto getStore() {
		return store;
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

	public void setStore(StoreDto store) {
		this.store = store;
	}

	public void setDeliveryBoy(DeliveryBoyDto deliveryBoy) {
		this.deliveryBoy = deliveryBoy;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
}

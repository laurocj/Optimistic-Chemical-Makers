package com.optimisticchemicalmakers.mapfood.bean;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;

public class WaitingOrders {

	public Map<Date, DeliveryRoute> map;
	
	public WaitingOrders getWaitingOrders() {
		return new WaitingOrders();
	}

	public Map<Date, DeliveryRoute> getMap() {
		return map;
	}

	public void setMap(Map<Date, DeliveryRoute> map) {
		this.map = map;
	}
}

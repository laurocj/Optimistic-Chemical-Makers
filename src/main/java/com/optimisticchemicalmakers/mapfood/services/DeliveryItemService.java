package com.optimisticchemicalmakers.mapfood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimisticchemicalmakers.mapfood.models.DeliveryItem;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryItemRepository;

@Service
public class DeliveryItemService {
	
	@Autowired
    private DeliveryItemRepository deliveryItemRepository;

	public DeliveryItem save(DeliveryItem deliveryItem) {
		return deliveryItemRepository.save(deliveryItem);
	}
	
	
}

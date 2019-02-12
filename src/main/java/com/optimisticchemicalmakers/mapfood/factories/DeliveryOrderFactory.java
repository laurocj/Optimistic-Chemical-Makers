package com.optimisticchemicalmakers.mapfood.factories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryItemDto;
import com.optimisticchemicalmakers.mapfood.dtos.DeliveryOrderDto;
import com.optimisticchemicalmakers.mapfood.models.Customer;
import com.optimisticchemicalmakers.mapfood.models.DeliveryItem;
import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.Store;

@Component
public class DeliveryOrderFactory {

    @Autowired
    public DeliveryItemFactory deliveryItemFactory;

    public DeliveryOrder getInstance(DeliveryOrderDto deliveryOrderDto) {

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setLatitude(deliveryOrderDto.getEndingLatitude());
        deliveryOrder.setLongitude(deliveryOrderDto.getEndingLongitude());
        deliveryOrder.setCustomer(new Customer(deliveryOrderDto.getCustomerId()));
        
        Set<DeliveryItem> deliveryItems = StreamSupport.stream(deliveryOrderDto.getDeliveryItems().spliterator(), false)
                .map(deliveryItemFactory::getInstance)
                .collect(Collectors.toSet());
        
        deliveryItems.forEach(deliveryItem -> deliveryItem.setDeliveryOrder(deliveryOrder));
        deliveryOrder.setDeliveryItems(deliveryItems);
        return deliveryOrder;

    }
    
    public DeliveryOrderDto getInstance(DeliveryOrder deliveryOrder, Store store) {
    	DeliveryOrderDto deliveryOrderDto = this.getInstance(deliveryOrder);
    	deliveryOrderDto.setDistance(deliveryOrder.distanceTo(store));
        return deliveryOrderDto;
    }
    
    public DeliveryOrderDto getInstance(DeliveryOrder deliveryOrder) {

        DeliveryOrderDto deliveryOrderDto = new DeliveryOrderDto();
        deliveryOrderDto.setId(deliveryOrder.getId());
        deliveryOrderDto.setStoreHash(deliveryOrder.getStore().getHash());
        deliveryOrderDto.setEstimatedDevliveryTime(deliveryOrder.getEstimatedDeliveryTime());
        deliveryOrderDto.setCustomerId(deliveryOrder.getCustomer().getId());
        deliveryOrderDto.setEndingLatitude(deliveryOrder.getLatitude());
        deliveryOrderDto.setEndingLongitude(deliveryOrder.getLongitude());

        Set<DeliveryItemDto> deliveryItems = StreamSupport.stream(deliveryOrder.getDeliveryItems().spliterator(), false)
                .map(deliveryItemFactory::getInstance)
                .collect(Collectors.toSet());

        deliveryOrderDto.setDeliveryItems(deliveryItems);

        return deliveryOrderDto;

    }
}
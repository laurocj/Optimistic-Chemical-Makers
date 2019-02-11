package com.optimisticchemicalmakers.mapfood.factories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryOrderDto;
import com.optimisticchemicalmakers.mapfood.dtos.DeliveryRouteDto;
import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;


@Component
public class DeliveryRouteFactory {
	
	@Autowired
	private DeliveryOrderFactory deliveryOrderFactory;

    public DeliveryRouteDto getInstance(DeliveryRoute deliveryRoute) {
    	DeliveryRouteDto deliveryRouteDto = new DeliveryRouteDto();
    	deliveryRouteDto.setClosedAt(deliveryRoute.getClosedAt());
    	
    	deliveryRouteDto.setStoreHash(deliveryRoute.getStore().getHash());
    	
    	List<DeliveryOrderDto> deliveryOrderDtos = StreamSupport
    			.stream(deliveryRoute.getDeliveryOrders().spliterator(), false)
                .map(deliveryOrderFactory::getInstance)
                .collect(Collectors.toList());
    	
    	deliveryRouteDto.setDeliveryOrders(deliveryOrderDtos);
    	
        return deliveryRouteDto;
    }

    public DeliveryRoute getInstance(DeliveryRouteDto deliveryRouteDto) {
    	
    	DeliveryRoute deliveryRoute = new DeliveryRoute();
    	deliveryRoute.setClosedAt(deliveryRouteDto.getClosedAt());
    	
    	List<DeliveryOrder> deliveryOrder = StreamSupport
    			.stream(deliveryRouteDto.getDeliveryOrders().spliterator(), false)
                .map(deliveryOrderFactory::getInstance)
                .collect(Collectors.toList());
    	
    	deliveryRoute.setDeliveryOrders(deliveryOrder);
        return deliveryRoute;
    }

}

package com.optimisticchemicalmakers.mapfood.services;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryOrderDto;
import com.optimisticchemicalmakers.mapfood.factories.DeliveryOrderFactory;
import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.Store;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryOrderRepository;

@Service
public class DeliveryOrderService {

    @Autowired
    private StoreService storeService;

    @Autowired
    private DeliveryOrderFactory deliveryOrderFactory;

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    
    @Resource(name = "requestScopedDeliveryRouteBean")
    DeliveryRouteService deliveryRouteService;

    // Service Methods

    public DeliveryOrder createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        
    	Long estimatedPreparationTime = 600000L; // 10 min
    	Long kmTimeTraveled = 120000L; // 2 min
    	
    	DeliveryOrder newDeliveryOrder = deliveryOrderFactory.getInstance(deliveryOrderDto);

    	Store store = storeService.getStore(deliveryOrderDto.getStoreHash());
    	newDeliveryOrder.setStore(store);

    	Double distance = store.distanceTo(newDeliveryOrder.getLatitude(), newDeliveryOrder.getLongitude());
    	
    	newDeliveryOrder.start();
    	
    	Date estimatedTime = new Date();
    	estimatedTime.setTime((long) (newDeliveryOrder.getCreatedAt().getTime() + (distance * kmTimeTraveled) + estimatedPreparationTime));
    	newDeliveryOrder.setEstimatedDeliveryTime(estimatedTime);
    	deliveryOrderDto.setEstimatedDevliveryTime(estimatedTime);

    	newDeliveryOrder = deliveryOrderRepository.save(newDeliveryOrder);

        store = storeService.save(store);
        
        newDeliveryOrder.setStore(store);
        
        deliveryRouteService.addOrder(newDeliveryOrder);
       
        return newDeliveryOrder;
    }
}
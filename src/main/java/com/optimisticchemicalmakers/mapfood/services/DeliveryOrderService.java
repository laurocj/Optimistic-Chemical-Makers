package com.optimisticchemicalmakers.mapfood.services;

import java.util.Date;

import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;
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
    @Autowired
    private DeliveryRouteService deliveryRouteService;

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


    public DeliveryRoute signAsReadyToDelivery(DeliveryOrderDto deliveryOrderDto) {

        DeliveryOrder deliveryOrder = this.getDeliveryOrder(deliveryOrderDto);

        deliveryOrder.setAsReadyToDelivery();

        deliveryOrderRepository.save(deliveryOrder);

        DeliveryRoute deliveryRoute =  deliveryRouteService.getDeliveryRoute(deliveryOrder);

        if(deliveryRoute.isReadyToDelivery()) {
            return this.deliveryRouteService.assignDeliveryBoy(deliveryRoute);
        } else {
            return deliveryRoute;
        }

    }

    public DeliveryOrder getDeliveryOrder(Long id) {
        return deliveryOrderRepository.findById(id).get();
    }

    public DeliveryOrder getDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        return this.getDeliveryOrder(deliveryOrderDto.getId());
    }


}
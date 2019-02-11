package com.optimisticchemicalmakers.mapfood.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimisticchemicalmakers.mapfood.bean.WaitingOrders;
import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;
import com.optimisticchemicalmakers.mapfood.models.Store;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryRouterRepository;

@Service
public class DeliveryRouteService {
	
	@Autowired
	public DeliveryRouterRepository deliveryRouterRepository;
	
	@Autowired
	public StoreService storeService;
	
	@Resource(name = "getWaitingOrders")
	WaitingOrders waitingOrders;
	
	public DeliveryRoute addOrder(DeliveryOrder newDeliveryOrder) {
		
		Map<Date, DeliveryRoute> map = waitingOrders.getMap();
		
		DeliveryRoute deliveryRoute;
		if(map == null)
			map = new HashMap<Date, DeliveryRoute>();
        
		Date keyWhereToAdd = null;
		
        if(!map.isEmpty()) {
        	
        	Long delay = 30000L; // 30 segundo
        	  	
        	Date termEstimateMoreDelay = new Date();
        	
        	outerloop:
        	for (Date key : map.keySet()) {
        		 
        		termEstimateMoreDelay.setTime(key.getTime() + delay);
        		
        		DeliveryRoute dRoute = map.get(key);
        		
        		if( Long.compare(newDeliveryOrder.getStore().getId(),dRoute.getStore().getId()) == 0
        			&& termEstimateMoreDelay.after(newDeliveryOrder.getCreatedAt())
    				&& dRoute.getDeliveryOrders().size() < 5) {
        			
        			for (DeliveryOrder orderAwaiting : dRoute.getDeliveryOrders()) {        				
        				
        				if(newDeliveryOrder.distanceTo(orderAwaiting) < newDeliveryOrder.distanceTo(dRoute.getStore())){
        					keyWhereToAdd = key;
        					break outerloop;
        				}
        			}
        		}
			}
        }
        
        if(keyWhereToAdd == null) {
    		
			deliveryRoute = new DeliveryRoute(newDeliveryOrder.getStore());
        	deliveryRoute.addDeliveryPoint(newDeliveryOrder);
        	map.put(newDeliveryOrder.getCreatedAt(), deliveryRoute);
        	
		} else {
			map.get(keyWhereToAdd).addDeliveryPoint(newDeliveryOrder);
			deliveryRoute = map.get(keyWhereToAdd);
		}
        
        newDeliveryOrder.setDeliveryRoute(deliveryRoute);
        
        deliveryRouterRepository.save(deliveryRoute);
        
        waitingOrders.setMap(map);
                
        return deliveryRoute;
	}

	public List<DeliveryRoute> getDeliveryRouteByStore(String hash_store) {
		Store store = storeService.getStore(hash_store);
		return deliveryRouterRepository.findByStore(store);
	}

}

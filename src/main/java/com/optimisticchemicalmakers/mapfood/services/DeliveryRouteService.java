package com.optimisticchemicalmakers.mapfood.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryRouterRepository;

@Component
public class DeliveryRouteService {
	
	@Autowired
	public DeliveryRouterRepository deliveryRouterRepository;
	
	public Map<Date, DeliveryRoute> map;
		
	@Bean
	@ApplicationScope
	public DeliveryRouteService requestScopedDeliveryRouteBean() {
		return new DeliveryRouteService();
	}
	
	
	public DeliveryRoute addOrder(DeliveryOrder newDeliveryOrder) {
				
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
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
        		
        		if( termEstimateMoreDelay.after(newDeliveryOrder.getCreatedAt())
    				&& map.get(key).getDeliveryOrders().size() < 5) {
        			
        			for (DeliveryOrder orderAwaiting : map.get(key).getDeliveryOrders()) {
        				DeliveryRoute dRoute = map.get(key);
        				
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
                
        return deliveryRoute;
	}

}

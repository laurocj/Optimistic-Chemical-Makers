package com.optimisticchemicalmakers.mapfood.services;

import java.util.*;

import javax.annotation.Resource;

import com.optimisticchemicalmakers.mapfood.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimisticchemicalmakers.mapfood.bean.WaitingOrders;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryRouterRepository;

@Service
public class DeliveryRouteService {
	
	@Autowired
	public DeliveryRouterRepository deliveryRouterRepository;
	
	@Autowired
	public StoreService storeService;

	@Autowired
	public DeliveryBoyService deliveryBoyService;
	
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

	// Get Optimized Delivery Route
	public List<Geolocation> getOptimizedDeliveryRoute(DeliveryRoute deliveryRoute) {

		DeliveryOrder nextDeliveryOrder = null;

		List<DeliveryOrder> deliveryOrders = new ArrayList<>(deliveryRoute.getDeliveryOrders());

		List<Geolocation> optimizedRoute = new ArrayList<>();

		optimizedRoute.add(deliveryRoute.getStore());

		while (deliveryOrders.size() > 0) {

			for (int i = 0; i < deliveryOrders.size(); i++) {

				if (nextDeliveryOrder == null) {

					nextDeliveryOrder = deliveryOrders.get(i);

				} else {

					if ( nextDeliveryOrder.distanceTo(deliveryRoute.getStore()) > deliveryOrders.get(i).distanceTo(deliveryRoute.getStore())) {
						nextDeliveryOrder = deliveryOrders.get(i);
					}

					deliveryOrders.get(i).distanceTo(deliveryRoute.getStore());

					nextDeliveryOrder.distanceTo(deliveryRoute.getStore());

				}

			}

			optimizedRoute.add(nextDeliveryOrder);

			deliveryOrders.remove(nextDeliveryOrder);

			nextDeliveryOrder = null;

		}

		return optimizedRoute;

	}

	public List<DeliveryRoute> getDeliveryRouteByStore(String hash_store) {
		Store store = storeService.getStore(hash_store);
		return deliveryRouterRepository.findByStore(store);
	}

	public DeliveryRoute getDeliveryRoute(DeliveryOrder deliveryOrder) {
		return this.getDeliveryRoute(deliveryOrder.getId());
	}

	public DeliveryRoute getDeliveryRoute(Long id) {
		return deliveryRouterRepository.findById(id).get();
	}

	public DeliveryRoute assignDeliveryBoy(DeliveryRoute deliverRoute, DeliveryBoy deliveryBoy) {
		deliverRoute.setDeliveryBoy(deliveryBoy);
		return deliveryRouterRepository.save(deliverRoute);
	}

	public DeliveryRoute assignDeliveryBoy(DeliveryRoute deliveryRoute) {
		return this.assignDeliveryBoy(deliveryRoute, deliveryBoyService.getNearestDeliveryBoy(
				deliveryRoute.getStore().getLatitude(), deliveryRoute.getStore().getLongitude()));
	}




}

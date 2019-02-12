package com.optimisticchemicalmakers.mapfood.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.google.maps.model.DirectionsResult;
import com.optimisticchemicalmakers.mapfood.dtos.DeliveryOrderDto;
import com.optimisticchemicalmakers.mapfood.factories.DeliveryOrderFactory;
import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;
import com.optimisticchemicalmakers.mapfood.models.Store;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryOrderRepository;

@Service
@CrossOrigin(origins = "http://localhost:4200") // temporary for testing in APP angular
public class DeliveryOrderService {

	// -----------------------------------------------------------------------------------------------------------------
    // Repository
    // -----------------------------------------------------------------------------------------------------------------

	@Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
	
	// -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private StoreService storeService;
    
    @Autowired
    private DeliveryRouteService deliveryRouteService;
    
    @Autowired
    private DeliveryBoyService deliveryBoyService;

    @Autowired
    private RouteService googleService;
    
    // -----------------------------------------------------------------------------------------------------------------
    // Factories
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private DeliveryOrderFactory deliveryOrderFactory;
    
    /**
     * createDeliveryOrder
     * 
     * @param deliveryOrderDto
     * @return DeliveryOrder
     */
    public DeliveryOrder createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        
    	Long estimatedPreparationTime = 600000L; // 10 min
    	Long kmTimeTraveled = 120000L; // 2 min
    
    	DeliveryOrder newDeliveryOrder = deliveryOrderFactory.getInstance(deliveryOrderDto);
    	
    	Store store = storeService.getStore(deliveryOrderDto.getStoreHash());
    	newDeliveryOrder.setStore(store);

    	Double distance = store.distanceTo(newDeliveryOrder.getLatitude(), newDeliveryOrder.getLongitude());
    	
    	DeliveryBoy deliveryBoy = deliveryBoyService.getNearestDeliveryBoy(newDeliveryOrder.getLatitude(), newDeliveryOrder.getLongitude());
		
    	if(deliveryBoy != null)
			distance = distance + newDeliveryOrder.distanceTo(deliveryBoy.getLatitude(), deliveryBoy.getLongitude()); 
    			
    	newDeliveryOrder.start();
    	
    	Date estimatedTime = new Date();
    	estimatedTime.setTime((long) (newDeliveryOrder.getCreatedAt().getTime() + (distance * kmTimeTraveled) + estimatedPreparationTime));

    	newDeliveryOrder.setEstimatedDeliveryTime(estimatedTime);

    	newDeliveryOrder = deliveryOrderRepository.save(newDeliveryOrder);

        store = storeService.save(store);
        
        newDeliveryOrder.setStore(store);
        
        deliveryRouteService.addOrder(newDeliveryOrder);
       
        return newDeliveryOrder;
    }


    /**
     * signAsReadyToDelivery
     * 
     * @param deliveryOrderDto
     * @return DeliveryRoute
     */
    public DeliveryRoute signAsReadyToDelivery(DeliveryOrderDto deliveryOrderDto) {

        DeliveryOrder deliveryOrder = this.getDeliveryOrder(deliveryOrderDto);

        deliveryOrder.setAsReadyToDelivery();

        deliveryOrderRepository.save(deliveryOrder);

        DeliveryRoute deliveryRoute =  deliveryRouteService.getDeliveryRoute(deliveryOrder);

        if(deliveryRoute.isReadyToDelivery()) {
        	
        	deliveryRoute = this.deliveryRouteService.assignDeliveryBoy(deliveryRoute);
            
        	DirectionsResult dr = googleService.getBestRoute(deliveryRoute);
        	
        	if(dr != null) {
	        	List<DeliveryOrder> dOrdes = deliveryRoute.getDeliveryOrders();	
				for (int j = 0; j < dr.routes[0].waypointOrder.length; j++) {
					dOrdes.get(j).setClassificationOnDelivery(dr.routes[0].waypointOrder[j] + 1);
				}
				dOrdes.get(dOrdes.size() - 1).setClassificationOnDelivery(dOrdes.size());
				deliveryRoute.setDeliveryOrders(dOrdes);
				
        	} else {
        		deliveryRoute = deliveryRouteService.getOptimizedDeliveryRoute(deliveryRoute);
        	}
        	
        	deliveryOrderRepository.save(deliveryOrder);
        }

        return deliveryRoute;
    }
    
    
	/**
	 * getDeliveryOrder
	 * 
	 * @param id
	 * @return DeliveryOrder 
	 */
    public DeliveryOrder getDeliveryOrder(Long id) {
        return deliveryOrderRepository.findById(id).get();
    }

    /**
     * getDeliveryOrder
     * 
     * @param deliveryOrderDto
     * @return DeliveryOrder
     */
    public DeliveryOrder getDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        return this.getDeliveryOrder(deliveryOrderDto.getId());
    }


}
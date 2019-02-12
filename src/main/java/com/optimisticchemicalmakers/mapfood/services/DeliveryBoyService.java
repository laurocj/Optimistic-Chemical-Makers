package com.optimisticchemicalmakers.mapfood.services;

import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryBoyService {

	// -----------------------------------------------------------------------------------------------------------------
    // Repository
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private DeliveryBoyRepository deliveryBoyRepository;
    
    // -----------------------------------------------------------------------------------------------------------------
    // Service
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    private DeliveryRouteService deliveryRouteService;

    /**
     * NearestDeliveryBoys
     * 
     * @param latitude
     * @param longitude
     * @param radius
     * @return List<DeliveryBoy>
     */
    public List<DeliveryBoy> getNearestDeliveryBoys(Double latitude, Double longitude, Double radius,int maxItems) {
        return deliveryBoyRepository.getNearestDeliveryBoys(latitude, longitude, radius, maxItems);
    }
    
    /**
     * NearestDeliveryBoys
     * 
     * @param latitude
     * @param longitude
     * @param radius
     * @return List<DeliveryBoy>
     */
    public List<DeliveryBoy> getNearestDeliveryBoys(Double latitude, Double longitude, Double radius) {
        return deliveryBoyRepository.getNearestDeliveryBoys(latitude, longitude, radius);
    }
    
    /**
     * getNearestDeliveryBoy
     * 
     * @param latitude
     * @param longitude
     * @return DeliveryBoy
     */
    public DeliveryBoy getNearestDeliveryBoy(Double latitude, Double longitude) {
    	return this.getNearestDeliveryBoy(latitude, longitude);
    }

    /**
     * getNearestDeliveryBoy
     * 
     * @param latitude
     * @param longitude
     * @return DeliveryBoy
     */
    public DeliveryBoy getNearestDeliveryBoy(Double latitude, Double longitude, int maxItems, Double andLatitude, Double andLongitude) {

        DeliveryBoy deliveryBoy = null;

        Double radius = 1.0;

        while (deliveryBoy == null) {

            radius++;
            
            List<DeliveryBoy> deliveryBoys = deliveryBoyRepository.getNearestDeliveryBoysOnTheWay(latitude, longitude, radius, maxItems, andLatitude, andLongitude);
            
            if(deliveryBoys.isEmpty())
            	deliveryBoys = this.getNearestDeliveryBoys(latitude, longitude, radius, maxItems);
            
            if (deliveryBoys.size() != 0) deliveryBoy = deliveryBoys.get(0);

            if (radius >= 10) break;

        }

        if (deliveryBoy == null) {

            return null;

        } else {

            return deliveryBoy;
        }

    }

    /**
     * Close Router
     * 
     * @param hash
     * @return Boolean
     */
	public Boolean getCloseRouter(String hash) {
		DeliveryRoute deliveryRoute = deliveryRouteService.getDeliveryRoute(hash);
		deliveryRoute.closeDeliveryRoute();
		return true;
	}

}

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
    public List<DeliveryBoy> getNearestDeliveryBoysFree(Double latitude, Double longitude, Double radius) {
        return deliveryBoyRepository.getNearestDeliveryBoysFree(latitude, longitude, radius);
    }

    /**
     * Nearest Delivery Boys On The Way
     * 
     * @param startLatitude
     * @param startLongitude
     * @param radius
     * @param maxItems
     * @param endLatitude
     * @param endLongitude
     * @return List<DeliveryBoy>
     */
    public List<DeliveryBoy> getNearestDeliveryBoysOnTheWay(Double startLatitude, Double startLongitude,Double radius, int maxItems, Double endLatitude, Double endLongitude) {
    	return deliveryBoyRepository.getNearestDeliveryBoysOnTheWay(startLatitude, startLongitude, radius, maxItems, endLatitude, endLongitude);
    }


    /**
     * Nearest DeliveryBoy Boys Free By Start Position And End Position
     * 
     * @param latitude
     * @param longitude
     * @param maxItems
     * @param endLatitude
     * @param endLongitude
     * @return DeliveryBoy
     */
    public DeliveryBoy getNearestDeliveryBoyBoysFreeByStartPosition(Double latitude, Double longitude, int maxItems, Double endLatitude, Double endLongitude) {

        DeliveryBoy deliveryBoy = null;

        Double radius = 1.0;

        while (deliveryBoy == null) {

            radius++;
            
            List<DeliveryBoy> deliveryBoys = this.getNearestDeliveryBoysOnTheWay(latitude, longitude, radius, maxItems, endLatitude, endLongitude);
            
            if(deliveryBoys.isEmpty())
            	deliveryBoys = this.getNearestDeliveryBoysFree(latitude, longitude, radius);
            
            if (deliveryBoys.size() != 0) deliveryBoy = deliveryBoys.get(0);

            if (radius >= 10) break;

        }


        return deliveryBoy;
    }

    /**
     * getNearestDeliveryBoy
     * 
     * @param latitude
     * @param longitude
     * @return DeliveryBoy
     */
    public DeliveryBoy getNearestDeliveryBoy(Double latitude, Double longitude) {

        DeliveryBoy deliveryBoy = null;

        Double radius = 1.0;

        while (deliveryBoy == null) {

            radius++;
            
            List<DeliveryBoy> deliveryBoys = this.getNearestDeliveryBoysFree(latitude, longitude, radius);
            
            if (deliveryBoys.size() != 0) deliveryBoy = deliveryBoys.get(0);

            if (radius >= 10) break;

        }

        return deliveryBoy;
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

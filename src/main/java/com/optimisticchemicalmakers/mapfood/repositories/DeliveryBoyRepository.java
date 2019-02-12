package com.optimisticchemicalmakers.mapfood.repositories;

import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryBoyRepository extends CrudRepository<DeliveryBoy, Long> {

	static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))";

    @Query(value="SELECT db.*, "+HAVERSINE_PART+" AS distance "
    		+ "FROM delivery_boy db "
    		+ "WHERE db.id NOT IN  ( "
    		+ "		SELECT dr.delivery_boy FROM delivery_route dr "
    		+ "		JOIN delivery_order do ON do.delivery_route_id = dr.id "
    		+ "		JOIN delivery_item di ON di.delivery_order_id = do.id "
    		+ "		WHERE dr.delivery_boy IS NOT NULL "
    		+ "		GROUP BY dr.delivery_boy"
    		+ "		HAVING COUNT(di.product_id) >= :notitens) "
    		+ "HAVING distance <= :radius "
    		+ "ORDER BY distance LIMIT 1 ",nativeQuery = true)
    List<DeliveryBoy> getNearestDeliveryBoys(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") Double radius, @Param("notitens") int notitens);

    @Query(value="SELECT db.*, "+HAVERSINE_PART+" AS distance "
    		+ "FROM delivery_boy db "
    		+ "HAVING distance <= :radius "
    		+ "ORDER BY distance LIMIT 1 ",nativeQuery = true)
	List<DeliveryBoy> getNearestDeliveryBoys(Double latitude, Double longitude, Double radius);

    
}

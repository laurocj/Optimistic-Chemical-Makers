package com.optimisticchemicalmakers.mapfood.repositories;

import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryBoyRepository extends CrudRepository<DeliveryBoy, Long> {

	static final String HAVERSINE_PART_BOY = "(6371 * acos(cos(radians(:latitude)) * cos(radians(db.latitude)) * cos(radians(db.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(db.latitude))))";

	static final String HAVERSINE_PART_ORDER = "(6371 * acos(cos(radians(:endLatitude)) * cos(radians(do.latitude)) * cos(radians(do.longitude) - radians(:endLongitude)) + sin(radians(:endLatitude)) * sin(radians(do.latitude))))";

    @Query(value="	SELECT db.*,"
    		+		HAVERSINE_PART_BOY +" AS startDistance ,"
    		+		HAVERSINE_PART_ORDER +" AS endDistance "
    		+ "		FROM delivery_route dr "
    		+ "		JOIN delivery_order do ON do.delivery_route_id = dr.id "
    		+ "		JOIN delivery_item di ON di.delivery_order_id = do.id "
    		+ "		JOIN delivery_boy db ON db.id = dr.delivery_boy"
    		+ "		GROUP BY dr.delivery_boy"
    		+ "		HAVING startDistance <= :radius AND COUNT(di.product_id) <= :itens AND endDistance <= 3 "
    		+ "		ORDER BY startDistance",nativeQuery = true)
    List<DeliveryBoy> getNearestDeliveryBoysOnTheWay(
    		@Param("latitude") Double latitude, 
    		@Param("longitude") Double longitude, 
    		@Param("radius") Double radius, 
    		@Param("itens") int notitens,
    		@Param("endLatitude") Double endLatitude,
    		@Param("endLongitude") Double endLongitude);

    @Query(value="SELECT db.*, "+HAVERSINE_PART_BOY+" AS distance "
    		+ "FROM delivery_boy db "
    		+ "JOIN delivery_route dr ON db.id = dr.delivery_boy "
    		+ "JOIN delivery_order do ON do.delivery_route_id = dr.id "
    		+ "JOIN delivery_item di ON di.delivery_order_id = do.id "
    		+ "GROUP BY dr.delivery_boy "
    		+ "HAVING COUNT(di.product_id) <= :itens AND distance <= :radius "
    		+ "ORDER BY distance LIMIT 3 ",nativeQuery = true)
    List<DeliveryBoy> getNearestDeliveryBoys(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") Double radius, @Param("itens") int itens);

    @Query(value="SELECT db.*, "+HAVERSINE_PART_BOY+" AS distance "
    		+ "FROM delivery_boy db "
    		+ "HAVING distance <= :radius "
    		+ "ORDER BY distance LIMIT 3 ",nativeQuery = true)
	List<DeliveryBoy> getNearestDeliveryBoys(Double latitude, Double longitude, Double radius);

    
}

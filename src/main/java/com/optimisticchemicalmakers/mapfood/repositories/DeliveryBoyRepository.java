package com.optimisticchemicalmakers.mapfood.repositories;

import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryBoyRepository extends CrudRepository<DeliveryBoy, Long> {

    String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(d.latitude)) * cos(radians(d.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(d.latitude))))";

    @Query(value="SELECT d.*, "+HAVERSINE_PART+" AS distance FROM delivery_boy d HAVING distance <= :distance order by distance",nativeQuery = true)
    List<DeliveryBoy> getNearestDeliveryBoys(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Long distance);


}

package com.optimisticchemicalmakers.mapfood.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.optimisticchemicalmakers.mapfood.models.Store;

public interface StoreRepository extends CrudRepository<Store, Long> {

    static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";

    Store getStoreByHash(String hash);
    
    @Query(value="SELECT s.*, "+HAVERSINE_PART+" AS distance FROM store s HAVING distance <= :distance order by distance",nativeQuery = true)
    List<Store> getNearestStores(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") Long distance);
 

}
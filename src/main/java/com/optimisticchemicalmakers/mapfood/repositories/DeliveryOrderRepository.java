package com.optimisticchemicalmakers.mapfood.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.Store;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Long> {

	List<DeliveryOrder> findByStoreAndCreatedAtBetween(Store store, Date start, Date end);
}
package com.optimisticchemicalmakers.mapfood.repositories;

import org.springframework.data.repository.CrudRepository;

import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Long> {
}
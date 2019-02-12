package com.optimisticchemicalmakers.mapfood.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.optimisticchemicalmakers.mapfood.models.DeliveryOrder;
import com.optimisticchemicalmakers.mapfood.models.DeliveryRoute;
import com.optimisticchemicalmakers.mapfood.models.Store;

public interface DeliveryRouterRepository extends CrudRepository<DeliveryRoute, Long>{

	List<DeliveryRoute> findByStore(Store store);

	DeliveryRoute findByDeliveryOrders(List<DeliveryOrder> deliveryOrders);

	DeliveryRoute findByHash(String hash);

}

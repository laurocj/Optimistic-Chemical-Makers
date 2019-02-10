package com.optimisticchemicalmakers.mapfood.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.optimisticchemicalmakers.mapfood.dtos.DeliveryItemDto;
import com.optimisticchemicalmakers.mapfood.models.DeliveryItem;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryOrderRepository;
import com.optimisticchemicalmakers.mapfood.repositories.ProductRepository;

@Component
public class DeliveryItemFactory {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;


    public DeliveryItem getInstance(DeliveryItemDto deliveryItemDto) {

        DeliveryItem deliveryItem = new DeliveryItem();

        deliveryItem.setQuantity(deliveryItemDto.getQuantity());

        deliveryItem.setProduct(productRepository.findById(deliveryItemDto.getProductId()).orElseThrow(RuntimeException::new));

        // Order
//        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(deliveryItemDto.getDeliveryOrderId()).orElseThrow(RuntimeException::new);
//        deliveryItem.setDeliveryOrder(deliveryOrder);

        // Return
        return deliveryItem;

    }

    public DeliveryItemDto getInstance(DeliveryItem deliveryItem) {

        DeliveryItemDto deliveryItemDto = new DeliveryItemDto();

        deliveryItemDto.setId(deliveryItem.getId());

        deliveryItemDto.setQuantity(deliveryItem.getQuantity());

        deliveryItemDto.setProductId(deliveryItem.getProduct().getId());

        deliveryItemDto.setDeliveryOrderId(deliveryItem.getProduct().getId());

        return deliveryItemDto;

    }


}

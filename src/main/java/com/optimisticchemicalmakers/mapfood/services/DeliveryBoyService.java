package com.optimisticchemicalmakers.mapfood.services;

import com.optimisticchemicalmakers.mapfood.models.DeliveryBoy;
import com.optimisticchemicalmakers.mapfood.repositories.DeliveryBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryBoyService {

    @Autowired
    DeliveryBoyRepository deliveryBoyRepository;

    public List<DeliveryBoy> getNearestDeliveryBoys(Double latitude, Double longitude, Long radius) {
        return deliveryBoyRepository.getNearestDeliveryBoys(latitude, longitude, radius);
    }

    public DeliveryBoy getNearestDeliveryBoy(Double latitude, Double longitude) {

        DeliveryBoy deliveryBoy = null;

        Long radius = 1L;

        while (deliveryBoy == null) {

            radius++;

            List<DeliveryBoy> deliveryBoys = this.getNearestDeliveryBoys(latitude, longitude, radius);

            if (deliveryBoys.size() != 0) deliveryBoy = deliveryBoys.get(0);

            if (radius >= 10) break;

        }

        if (deliveryBoy == null) {

            return null;

        } else {

            return deliveryBoy;
        }

    }

}

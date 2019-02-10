package com.optimisticchemicalmakers.mapfood.factories;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.optimisticchemicalmakers.mapfood.dtos.StoreDto;
import com.optimisticchemicalmakers.mapfood.models.Store;

@CrossOrigin(origins="http://localhost:4200") // Temporary for the Angular App test
@Component
public class StoreFactory {

    public StoreDto getInstance(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setHash(store.getHash());
        storeDto.setLatitude(store.getLatitude());
        storeDto.setLongitude(store.getLongitude());
        storeDto.setName(store.getName());
        storeDto.setDishDescription(store.getDishDescription());
        storeDto.setCity(store.getCity());
        storeDto.setHash(store.getHash());
        return storeDto;
    }

    public StoreDto getInstance(Store store, Double distance) {
        StoreDto storeDto = this.getInstance(store);
        storeDto.setRequestorDistance(distance);
        return storeDto;
    }

    public Store getInstance(StoreDto storeDto) {
        Store store = new Store();
        store.setHash(storeDto.getHash());
        store.setLatitude(storeDto.getLatitude());
        store.setLongitude(storeDto.getLongitude());
        store.setName(storeDto.getName());
        store.setCity(storeDto.getCity());
        store.setDishDescription(storeDto.getDishDescription());
        return store;
    }
}

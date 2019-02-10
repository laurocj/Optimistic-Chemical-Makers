package com.optimisticchemicalmakers.mapfood.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DeliveryRoute {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="deliveryRoute", cascade = CascadeType.ALL)
    private List<DeliveryOrder> deliveryOrders;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @OneToOne
    private DeliveryBoy deliveryBoy;

    private Date closedAt;

    // Constructors

    public DeliveryRoute(Store store) {
        this.store = store;
    }

    // Getters

    public Long getId() {
        return this.id;
    }

    public Store getStore() {
        return this.store;
    }

    public DeliveryBoy getDeliveryBoy() {
        return this.deliveryBoy;
    }

    public Date getClosedAt() {
        return this.closedAt;
    }

    // Methods

    public ArrayList<Geolocation> getOptimizedRoute() {
        // TO DO : Calcula melhor rota entre as deliveryOrders;
        // TO DO : Retorna uma ArrayList no qual o index representa a posição do ponto na rota
        // OBS1  : O restaurante sempre será o ponto zero;
        // OBS2  : Se houverem somente uma deliveryOrder só há uma possibilidade de retorno;
        // OBS1  : Uma DeliveryRoute pode conter de 1 a até 5 DeliveryOrders;
        return null;
    }

    public void assignDeliveryBoy(DeliveryBoy deliveryBoy) {
        // TO DO : Se o DeliveryBoy não está realizando outra entrega, pode associar.
        this.deliveryBoy = deliveryBoy;
    }

    public void addDeliveryPoint(DeliveryOrder deliveryOrder) {
        if (deliveryOrder.getId()   == this.store.getId() && this.deliveryOrders.size() <= 4) {
            this.deliveryOrders.add(deliveryOrder);
        } else {
            throw new RuntimeException();
        }
    }

    public void closeDeliveryRoute() {
        // TO DO : Fecha a rota apenas se todas as entregas da rota estão finalizadas
        this.closedAt = new Date();

    }

}
package com.optimisticchemicalmakers.mapfood.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    
    @Column(nullable = false)
    private String hash = UUID.randomUUID().toString().replace("-", "");

    @OneToMany(mappedBy="deliveryRoute", cascade = CascadeType.ALL)
    private List<DeliveryOrder> deliveryOrders;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @OneToOne
    private DeliveryBoy deliveryBoy;

    private Date closedAt;
   
    // Constructors
    
    public DeliveryRoute() {}

    public DeliveryRoute(Store store) {
        this.store = store;
    }

    // Gets
    
	public Long getId() {
		return id;
	}
	
	public String getHash() {
		return hash;
	}

	public List<DeliveryOrder> getDeliveryOrders() {
		return deliveryOrders;
	}

	public Store getStore() {
		return store;
	}

	public DeliveryBoy getDeliveryBoy() {
		return deliveryBoy;
	}

	public Date getClosedAt() {
		return closedAt;
	}

	// Sets 

	public void setDeliveryOrders(List<DeliveryOrder> deliveryOrders) {
		this.deliveryOrders = deliveryOrders;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setDeliveryBoy(DeliveryBoy deliveryBoy) {
		this.deliveryBoy = deliveryBoy;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
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
    	if(this.deliveryOrders == null)
    		this.deliveryOrders = new ArrayList<>();
    	
        if (Long.compare(deliveryOrder.getStore().getId(),this.store.getId()) == 0 && this.deliveryOrders.size() <= 4) {
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
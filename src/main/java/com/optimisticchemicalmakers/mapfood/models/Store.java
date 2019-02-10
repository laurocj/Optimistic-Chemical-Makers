package com.optimisticchemicalmakers.mapfood.models;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Store extends Geolocation implements Serializable {

	private static final long serialVersionUID = 1L;

	// Properties

    @Column(nullable = false)
    private String hash = UUID.randomUUID().toString().replace("-", "");

	@Id // It tells the JPA that it is an ID
	@GeneratedValue(strategy = GenerationType.IDENTITY) // It tells the JPA how to autogenerate the ID value
	private Long id;

	private String name;

	private String dishDescription;

	private String city;

	@OneToMany(mappedBy = "store")
	private Set<Product> products;

	@OneToMany(mappedBy = "store")
	private Set<DeliveryOrder> deliveryOrders;

	// Constructors

	public Store() {
	}
    
	public Store(Double latitude, Double longitude, String name, String dishDescription) {
		super(latitude, longitude);
		this.name = name;
		this.dishDescription = dishDescription;
	}

    public String getHash() {
        return this.hash;
    }

    public String getName() {
        return name;
    }

	public Long getId() {
		return id;
	}

	public String getDishDescription() {
		return dishDescription;
	}

	public String getCity() {
		return city;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public Set<DeliveryOrder> getDeliveryOrders() {
		return deliveryOrders;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDishDescription(String dishDescription) {
		this.dishDescription = dishDescription;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public void setDeliveryOrders(Set<DeliveryOrder> deliveryOrders) {
		this.deliveryOrders = deliveryOrders;
	}

	// Methods
	
	public void addDeliveryOrder(DeliveryOrder deliveryOrder) {
		this.deliveryOrders.add(deliveryOrder);
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}
}

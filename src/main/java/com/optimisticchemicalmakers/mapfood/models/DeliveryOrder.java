package com.optimisticchemicalmakers.mapfood.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DeliveryOrder extends Geolocation {

	// Class Properties

	@Id // It tells the JPA that it is an ID
	@GeneratedValue(strategy = GenerationType.IDENTITY) // It tells the JPA how to autogenerate the ID value
	private Long id;

	private Date createdAt;

	private Date acceptedAt;

	private Date preparedAt;

	private Date estimatedDeliveryTime;

	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL)
	private Set<DeliveryItem> deliveryItems;

    @ManyToOne
    @JoinColumn(name="delivery_route_id", nullable=true)
    private DeliveryRoute deliveryRoute;
    
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	// Constructors

	public DeliveryOrder() {

	}

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getAcceptedAt() {
		return acceptedAt;
	}

	public Date getPreparedAt() {
		return preparedAt;
	}

	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public Store getStore() {
		return store;
	}

	public Set<DeliveryItem> getDeliveryItems() {
		return deliveryItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setAcceptedAt(Date acceptedAt) {
		this.acceptedAt = acceptedAt;
	}

	public void setPreparedAt(Date preparedAt) {
		this.preparedAt = preparedAt;
	}

	public void setEstimatedDeliveryTime(Date estimatedDevliveryTime) {
		this.estimatedDeliveryTime = estimatedDevliveryTime;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setDeliveryItems(Set<DeliveryItem> deliveryItems) {
		this.deliveryItems = deliveryItems;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void start() {
		this.createdAt = new Date();
	}
}
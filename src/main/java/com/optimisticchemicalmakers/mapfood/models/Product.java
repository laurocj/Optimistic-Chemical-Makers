package com.optimisticchemicalmakers.mapfood.models;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product  implements Serializable {

	private static final long serialVersionUID = 1L;

    // Properties

    @Id                                                 // It tells the JPA that it is an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // It tells the JPA how to autogenerate the ID value
    private Long id;
   
    @Column(nullable = false)
    private String hash = UUID.randomUUID().toString().replace("-", "");
    
    private String description;

    private BigDecimal price;

    private String classification;
    
    @ManyToOne
    @JoinColumn(name="store_hash", nullable=false, referencedColumnName="hash")
    private Store store;

    // Constructors

    public Product() {
    }
    
    public Product(Store store, String description, BigDecimal price) {
        this.store = store;
        this.description = description;
        this.price = price;
    }

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getHash() {
		return hash;
	}

	public String getClassification() {
		return classification;
	}

	public Store getStore() {
		return store;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}

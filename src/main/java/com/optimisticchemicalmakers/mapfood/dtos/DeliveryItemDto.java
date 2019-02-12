package com.optimisticchemicalmakers.mapfood.dtos;

public class DeliveryItemDto {

    // Class Properties

    private Long id;

    private Long productId;

    private Long deliveryOrderId;

    private Integer quantity;
    
    private String productDescription;

    // Constructor

    public DeliveryItemDto() {
    }

	public Long getId() {
		return id;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setDeliveryOrderId(Long deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

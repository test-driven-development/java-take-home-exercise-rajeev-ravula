package com.ibm.cloud.garage.cart;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {

	private List<LineItem> lineItems;

	private int totalNumberOfItems;

	private BigDecimal salesTax;

	private BigDecimal totalPrice;

	public int getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

	public void setTotalNumberOfItems(int totalNumberOfItems) {
		this.totalNumberOfItems = totalNumberOfItems;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineItems == null) ? 0 : lineItems.hashCode());
		result = prime * result + ((salesTax == null) ? 0 : salesTax.hashCode());
		result = prime * result + totalNumberOfItems;
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		if (lineItems == null) {
			if (other.lineItems != null)
				return false;
		} else if (!lineItems.equals(other.lineItems))
			return false;
		if (salesTax == null) {
			if (other.salesTax != null)
				return false;
		} else if (!salesTax.equals(other.salesTax))
			return false;
		if (totalNumberOfItems != other.totalNumberOfItems)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

}

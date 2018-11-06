package com.ibm.cloud.garage.cart.service;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.cloud.garage.cart.LineItem;
import com.ibm.cloud.garage.cart.ShoppingCart;

public class ShoppingCartUtil {

	private ShoppingCartUtil() {

	}

	public static String buildReportFromShoppingCart(ShoppingCart cart) {
		StringBuilder report = new StringBuilder();
		List<LineItem> lineItems = cart.getLineItems();
		if (lineItems != null && !lineItems.isEmpty()) {
			for (LineItem lineItem : lineItems) {
				report.append(lineItem.getQuantity()).append(" ").append(lineItem.getProduct().isImported() ? "Imported " : "").append(lineItem.getProduct().getName())
						.append(": ")
						.append(lineItem.getTotalPrice()).append("\n");
			}
			report.append("\n");
			report.append("Sales Taxes: ").append(cart.getSalesTax()).append("\n");
			report.append("Total: ").append(cart.getTotalPrice());
		}
		return report.toString();
	}

	public static ShoppingCart buildShoppingCart(List<LineItem> lineItems) {
		ShoppingCart shoppingCart = new ShoppingCart();
		int totalNumberOfItems = 0;
		BigDecimal salesTax = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		if (lineItems != null && !lineItems.isEmpty()) {
			for (LineItem lineItem : lineItems) {
				totalNumberOfItems += lineItem.getQuantity();
				totalPrice = totalPrice.add(lineItem.getTotalPrice().multiply(new BigDecimal(lineItem.getQuantity())));
				salesTax = salesTax.add(lineItem.getTax());
			}
			shoppingCart.setLineItems(lineItems);
			shoppingCart.setTotalNumberOfItems(totalNumberOfItems);
			shoppingCart.setTotalPrice(totalPrice);
			shoppingCart.setSalesTax(salesTax);

		}
		return shoppingCart;
	}

}
package com.ibm.cloud.garage.cart.parser;

import java.math.BigDecimal;

import com.ibm.cloud.garage.cart.LineItem;
import com.ibm.cloud.garage.cart.Product;
import com.ibm.cloud.garage.cart.ProductTypes;

public class ParserUtil {

	private ParserUtil() {
	}

	public static Product buildProduct(String productName, BigDecimal unitPrice, boolean isImported) {
		Product product = new Product();
		product.setName(productName);
		product.setPrice(unitPrice);
		product.setImported(isImported);
		ProductTypes type = ProductTypes.getProductTypeMap().get(productName);
		product.setType(type);
		product.setSalesTax(
				isImported ? unitPrice.multiply(BigDecimal.valueOf(type.getTaxPercentage() + 0.05)) : unitPrice.multiply(BigDecimal.valueOf(type.getTaxPercentage())));
		return product;
	}

	public static LineItem buildLineItem(int quantity, Product product) {
		LineItem lineItem = new LineItem();
		lineItem.setQuantity(quantity);
		lineItem.setProduct(product);
		BigDecimal salesTax = roundSalesTaxToNearestNickle(product.getSalesTax().multiply(new BigDecimal(quantity)));
		BigDecimal basePrice = product.getPrice().multiply(new BigDecimal(quantity));
		lineItem.setBasePrice(basePrice);
		lineItem.setTax(salesTax);
		lineItem.setTotalPrice(basePrice.add(salesTax));
		return lineItem;
	}

	static BigDecimal roundSalesTaxToNearestNickle(BigDecimal salesTax) {
		return BigDecimal.valueOf(((double) (long) (salesTax.doubleValue() * 20 + 0.5)) / 20).setScale(2);
	}

}

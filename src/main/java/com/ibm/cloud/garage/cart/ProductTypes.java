package com.ibm.cloud.garage.cart;

import java.util.HashMap;
import java.util.Map;

public enum ProductTypes {

	BOOK("book", "books", 0.00),

	MUSIC_CD("music CD", "entertainment", 0.10),

	CHOCOLATE_BAR("chocolate bar", "food", 0.00),

	CHOCOLATE_BOX("box of chocolates", "food", 0.00),

	PERFUME("bottle of perfume", "cosmetics", 0.10),

	HEADHACHE_PILLS("packet of headache pills", "medical", 0.00);

	private final String productName;

	private final String productCategory;

	private final double taxPercentage;

	private static final Map<String, ProductTypes> productTypeMap;

	ProductTypes(String productName, String productCategory, double taxPercentage) {
		this.productName = productName;
		this.productCategory = productCategory;
		this.taxPercentage = taxPercentage;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	static {
		productTypeMap = new HashMap<>();
		for (ProductTypes productType : ProductTypes.values()) {
			productTypeMap.put(productType.getProductName(), productType);
		}
	}

	public static Map<String, ProductTypes> getProductTypeMap() {
		return productTypeMap;
	}

}

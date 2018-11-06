package com.ibm.cloud.garage.cart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import com.ibm.cloud.garage.cart.LineItem;
import com.ibm.cloud.garage.cart.ShoppingCart;
import com.ibm.cloud.garage.cart.parser.FileParser;
import com.ibm.cloud.garage.cart.parser.ParserFactory;

public class ShoppingCartService {

	public String buildCart(String fileName) throws FileNotFoundException {
		if (fileName != null && !fileName.trim().isEmpty()) {
			URL filePath = getClass().getClassLoader().getResource(fileName);
			File inputFile = new File(filePath != null ? filePath.getFile() : "");
			FileParser parser = ParserFactory.buildParserInstance(inputFile);
			if (parser != null) {
				List<LineItem> lineItems = parser.parseFile(inputFile);
				ShoppingCart cart = ShoppingCartUtil.buildShoppingCart(lineItems);
				return ShoppingCartUtil.buildReportFromShoppingCart(cart);
			}

		}
		return null;
	}

}
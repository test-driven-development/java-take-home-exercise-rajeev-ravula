package com.ibm.cloud.garage.cart.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibm.cloud.garage.cart.LineItem;
import com.ibm.cloud.garage.cart.Product;

public class SpaceDelimeterParser implements FileParser {

	private static final String SPACE_DELIMETER_PATTERN = "^([\\d\\s]+)([a-zA-Z\\s]+)([\\s\\d\\.\\d|\\d]+)$";

	@Override
	public List<LineItem> parseFile(File file) throws FileNotFoundException {
		List<LineItem> lineItems = new ArrayList<>();
		try (Scanner sc = new Scanner(file)) {
			skipFirstTwoLinesToGetOntoLineItems(sc);
			while (sc.hasNextLine()) {
				String lineItemEntry = sc.nextLine();
				lineItems.add(parseLineItem(lineItemEntry));
			}
		}
		return lineItems;
	}

	LineItem parseLineItem(String lineItemEntry) {
		LineItem lineItem = null;
		Pattern p = Pattern.compile(SPACE_DELIMETER_PATTERN);
		Matcher matcher = p.matcher(lineItemEntry);
		if (matcher.matches()) {
			int quantity = Integer.parseInt(matcher.group(1).trim());
			String productName = matcher.group(2).trim();
			BigDecimal unitPrice = new BigDecimal(matcher.group(3).trim());
			Product product = ParserUtil.buildProduct(productName, unitPrice, false);
			lineItem = ParserUtil.buildLineItem(quantity, product);
		}
		return lineItem;

	}

	private void skipFirstTwoLinesToGetOntoLineItems(Scanner sc) {
		sc.nextLine();
		sc.nextLine();
	}

}

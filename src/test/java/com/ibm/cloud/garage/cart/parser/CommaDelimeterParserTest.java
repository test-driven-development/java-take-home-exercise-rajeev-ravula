package com.ibm.cloud.garage.cart.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ibm.cloud.garage.cart.LineItem;

public class CommaDelimeterParserTest {

	private CommaDelimeterParser commaDelimeterParser;

	@Before
	public void before() {
		commaDelimeterParser = new CommaDelimeterParser();
	}

	@Test
	public void testParseFileWithValidFilename() throws FileNotFoundException {
		File inputFile = new File(getClass().getClassLoader().getResource("CommaDelimiterInput").getFile());
		List<LineItem> lineItems = commaDelimeterParser.parseFile(inputFile);
		assertEquals(4, lineItems.size());
	}

	@Test
	public void testParseFileWithInValidFilename() throws FileNotFoundException {
		File inputFile = new File(getClass().getClassLoader().getResource("CommaDelimiterInput2").getFile());
		List<LineItem> lineItems = commaDelimeterParser.parseFile(inputFile);
		assertEquals(5, lineItems.size());
	}

	@Test
	public void testParseLineItemValidEntry() {
		LineItem lineItem = commaDelimeterParser.parseLineItem("1 bottle of perfume, 27.99, imported");
		assertEquals(1, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(27.99), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(4.20).setScale(2), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(32.19), lineItem.getTotalPrice());
	}

	@Test
	public void testParseLineItemValidEntryImportedAsOptional() {
		LineItem lineItem = commaDelimeterParser.parseLineItem("1 bottle of perfume, 18.99");
		assertEquals(1, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(18.99), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(1.90).setScale(2), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(20.89), lineItem.getTotalPrice());
	}

	@Test
	public void testParseLineItemInvalidEntry() {
		LineItem lineItem = commaDelimeterParser.parseLineItem("Imported, 12.49, 1 bottle of perfume");
		assertNull(lineItem);
	}
}

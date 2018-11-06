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

public class SpaceDelimeterParserTest {

	private SpaceDelimeterParser spaceDelimeterParser;

	@Before
	public void before() {
		spaceDelimeterParser = new SpaceDelimeterParser();
	}

	@Test
	public void testParseFileWithValidFilename() throws FileNotFoundException {
		File inputFile = new File(getClass().getClassLoader().getResource("SpaceDelimiterInput").getFile());
		List<LineItem> lineItems = spaceDelimeterParser.parseFile(inputFile);
		assertEquals(3, lineItems.size());
	}

	@Test
	public void testParseFileWithInValidFilename() throws FileNotFoundException {
		File inputFile = new File(getClass().getClassLoader().getResource("SpaceDelimiterInput2").getFile());
		List<LineItem> lineItems = spaceDelimeterParser.parseFile(inputFile);
		assertEquals(4, lineItems.size());
	}

	@Test
	public void testParseLineItemValidEntry() {
		LineItem lineItem = spaceDelimeterParser.parseLineItem("1 book 12.49");
		assertEquals(1, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(12.49), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(0).setScale(2), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(12.49), lineItem.getTotalPrice());
	}

	@Test
	public void testParseLineItemInvalidEntry() {
		LineItem lineItem = spaceDelimeterParser.parseLineItem("book 2 Imported 12.49");
		assertNull(lineItem);
	}
}

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

public class PipeDelimeterParserTest {

	private PipeDelimeterParser pipeDelimeterParser;

	@Before
	public void before() {
		pipeDelimeterParser = new PipeDelimeterParser();
	}

	@Test
	public void testParseFileWithValidFilename() throws FileNotFoundException {
		File inputFile = new File(getClass().getClassLoader().getResource("PipeDelimiterInput").getFile());
		List<LineItem> lineItems = pipeDelimeterParser.parseFile(inputFile);
		assertEquals(2, lineItems.size());
	}

	@Test
	public void testParseFileWithInValidFilename() throws FileNotFoundException {
		File inputFile = new File(getClass().getClassLoader().getResource("PipeDelimiterInput2").getFile());
		List<LineItem> lineItems = pipeDelimeterParser.parseFile(inputFile);
		assertEquals(3, lineItems.size());
	}

	@Test
	public void testParseLineItemValidEntry() {
		LineItem lineItem = pipeDelimeterParser.parseLineItem("Imported | 1 bottle of perfume | 47.50");
		assertEquals(1, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(47.50).setScale(2), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(7.15).setScale(2), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(54.65), lineItem.getTotalPrice());
	}

	@Test
	public void testParseLineItemValidEntryImportedAsOptional() {
		LineItem lineItem = pipeDelimeterParser.parseLineItem("2 bottle of perfume | 47.33");
		assertEquals(2, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(94.66), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(9.45).setScale(2), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(104.11), lineItem.getTotalPrice());
	}

	@Test
	public void testParseLineItemInvalidEntry() {
		LineItem lineItem = pipeDelimeterParser.parseLineItem("book 2 | Imported | 12.49");
		assertNull(lineItem);
	}
}

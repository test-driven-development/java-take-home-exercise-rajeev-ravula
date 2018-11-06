package com.ibm.cloud.garage.cart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class ShoppingCartServiceTest {

	private ShoppingCartService shoppingCartService;

	@Before
	public void before() {
		shoppingCartService = new ShoppingCartService();
	}

	@Test
	public void testBuildCartSpaceDelimeter() throws FileNotFoundException {
		String fileName = "SpaceDelimiterInput";
		assertEquals("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\n\nSales Taxes: 1.50\nTotal: 29.83", shoppingCartService.buildCart(fileName));
	}

	@Test
	public void testBuildCartSpaceDelimeterWithAddedEntries() throws FileNotFoundException {
		String fileName = "SpaceDelimiterInput2";
		assertEquals("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\n2 box of chocolates: 21.70\n\nSales Taxes: 1.50\nTotal: 73.23",
				shoppingCartService.buildCart(fileName));
	}

	@Test(expected = FileNotFoundException.class)
	public void testBuildCartWithInvalidFileName() throws FileNotFoundException {
		String fileName = "SpaceDelimiterInput3";
		shoppingCartService.buildCart(fileName);
	}

	@Test
	public void testBuildCartPipeDelimeter() throws FileNotFoundException {
		String fileName = "PipeDelimiterInput";
		assertEquals("1 Imported bottle of perfume: 54.65\n1 Imported box of chocolates: 10.50\n\nSales Taxes: 7.65\nTotal: 65.15", shoppingCartService.buildCart(fileName));
	}

	@Test
	public void testBuildCartPipeDelimeterWithAddedEntries() throws FileNotFoundException {
		String fileName = "PipeDelimiterInput2";
		assertEquals("1 Imported bottle of perfume: 54.65\n1 Imported box of chocolates: 10.50\n1 box of chocolates: 5.00\n\nSales Taxes: 7.65\nTotal: 70.15",
				shoppingCartService.buildCart(fileName));
	}

	@Test
	public void testBuildCartCommaDelimeter() throws FileNotFoundException {
		String fileName = "CommaDelimiterInput";
		assertEquals(
				"1 Imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 Imported box of chocolates: 11.80\n\nSales Taxes: 6.65\nTotal: 74.63",
				shoppingCartService.buildCart(fileName));
	}

	@Test
	public void testBuildCartCommaDelimeterWithAddedEntries() throws FileNotFoundException {
		String fileName = "CommaDelimiterInput2";
		assertEquals(
				"1 Imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 Imported box of chocolates: 11.80\n1 book: 18.99\n\nSales Taxes: 6.65\nTotal: 93.62",
				shoppingCartService.buildCart(fileName));
	}

	@Test
	public void testBuildCartWithInvalidDelimeter() throws FileNotFoundException {
		String fileName = "InvalidDelimeter";
		assertNull(shoppingCartService.buildCart(fileName));
	}

}

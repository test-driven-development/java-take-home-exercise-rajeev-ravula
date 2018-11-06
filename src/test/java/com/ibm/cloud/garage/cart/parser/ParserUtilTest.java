package com.ibm.cloud.garage.cart.parser;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.ibm.cloud.garage.cart.LineItem;
import com.ibm.cloud.garage.cart.Product;
import com.ibm.cloud.garage.cart.ProductTypes;

public class ParserUtilTest {

	@Test
	public void testBuildProductImported() {
		Product productImported = ParserUtil.buildProduct("book", BigDecimal.valueOf(22.50), true);
		assertEquals("book", productImported.getName());
		assertEquals(BigDecimal.valueOf(22.50), productImported.getPrice());
		assertEquals(true, productImported.isImported());
		assertEquals(BigDecimal.valueOf(1.125), productImported.getSalesTax());
		assertEquals(ProductTypes.BOOK, productImported.getType());
	}

	@Test
	public void testBuildProductNotImported() {
		Product productNotImported = ParserUtil.buildProduct("book", BigDecimal.valueOf(22.50), false);
		assertEquals("book", productNotImported.getName());
		assertEquals(BigDecimal.valueOf(22.50), productNotImported.getPrice());
		assertEquals(false, productNotImported.isImported());
		assertEquals(BigDecimal.valueOf(0.00).setScale(2), productNotImported.getSalesTax());
		assertEquals(ProductTypes.BOOK, productNotImported.getType());
	}

	@Test
	public void testBuildProductNotImportedAndTaxable() {
		Product productNotImportedTaxable = ParserUtil.buildProduct("bottle of perfume", BigDecimal.valueOf(22.50), false);
		assertEquals("bottle of perfume", productNotImportedTaxable.getName());
		assertEquals(BigDecimal.valueOf(22.50), productNotImportedTaxable.getPrice());
		assertEquals(false, productNotImportedTaxable.isImported());
		assertEquals(BigDecimal.valueOf(2.25), productNotImportedTaxable.getSalesTax());
		assertEquals(ProductTypes.PERFUME, productNotImportedTaxable.getType());
	}

	@Test
	public void testBuildProductImportedAndTaxable() {
		Product productImportedTaxable = ParserUtil.buildProduct("bottle of perfume", BigDecimal.valueOf(22.50), true);
		assertEquals("bottle of perfume", productImportedTaxable.getName());
		assertEquals(BigDecimal.valueOf(22.50), productImportedTaxable.getPrice());
		assertEquals(true, productImportedTaxable.isImported());
		assertEquals(BigDecimal.valueOf(3.375), productImportedTaxable.getSalesTax().setScale(3, RoundingMode.HALF_UP));
		assertEquals(ProductTypes.PERFUME, productImportedTaxable.getType());
	}

	@Test
	public void testBuildLineItemWithQuantityOne() {
		Product product = ParserUtil.buildProduct("bottle of perfume", BigDecimal.valueOf(22.50), false);
		LineItem lineItem = ParserUtil.buildLineItem(1, product);
		assertEquals(1, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(22.50), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(2.25), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(24.75), lineItem.getTotalPrice());
	}

	@Test
	public void testBuildLineItemWithQuantityThree() {
		Product product = ParserUtil.buildProduct("bottle of perfume", BigDecimal.valueOf(22.50), false);
		LineItem lineItem = ParserUtil.buildLineItem(3, product);
		assertEquals(3, lineItem.getQuantity());
		assertEquals(BigDecimal.valueOf(67.50), lineItem.getBasePrice());
		assertEquals(BigDecimal.valueOf(6.75), lineItem.getTax());
		assertEquals(BigDecimal.valueOf(74.25), lineItem.getTotalPrice());
	}

	@Test
	public void testRoundSalesTaxToNearestNickleFloor() {
		assertEquals(BigDecimal.valueOf(2.55), ParserUtil.roundSalesTaxToNearestNickle(BigDecimal.valueOf(2.57)));
	}

	@Test
	public void testRoundSalesTaxToNearestNickleCeil() {
		assertEquals(BigDecimal.valueOf(2.60).setScale(2), ParserUtil.roundSalesTaxToNearestNickle(BigDecimal.valueOf(2.58)));
	}

	@Test
	public void testRoundSalesTaxToNearestNickleBoundary() {
		assertEquals(BigDecimal.valueOf(2.60).setScale(2), ParserUtil.roundSalesTaxToNearestNickle(BigDecimal.valueOf(2.60)));
	}

}

package com.ibm.cloud.garage.cart.parser;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ParserFactoryTest {

	@Test
	public void testBuildParserInstanceForSpace() throws FileNotFoundException {
		FileParser parser = ParserFactory.buildParserInstance(new File(getClass().getClassLoader().getResource("SpaceDelimiterInput").getFile()));
		assertTrue(parser instanceof SpaceDelimeterParser);
	}

	@Test
	public void testBuildParserInstanceForPipe() throws FileNotFoundException {
		FileParser parser = ParserFactory.buildParserInstance(new File(getClass().getClassLoader().getResource("PipeDelimiterInput").getFile()));
		assertTrue(parser instanceof PipeDelimeterParser);
	}

	@Test
	public void testBuildParserInstanceForComma() throws FileNotFoundException {
		FileParser parser = ParserFactory.buildParserInstance(new File(getClass().getClassLoader().getResource("CommaDelimiterInput").getFile()));
		assertTrue(parser instanceof CommaDelimeterParser);
	}

	@Test
	public void testBuildParserInstanceInvalidDelimeterType() throws FileNotFoundException {
		assertNull(ParserFactory.buildParserInstance(new File(getClass().getClassLoader().getResource("InvalidDelimeter").getFile())));
	}

}

package com.ibm.cloud.garage.cart.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParserFactory {

	private ParserFactory() {
	}

	public static FileParser buildParserInstance(File inputFile) throws FileNotFoundException {
		try (Scanner sc = new Scanner(inputFile)) {
			String delimeterType = sc.nextLine().trim();
			if (!delimeterType.isEmpty()) {
				if ("space:".equalsIgnoreCase(delimeterType))
					return new SpaceDelimeterParser();
				else if ("pipe:".equalsIgnoreCase(delimeterType))
					return new PipeDelimeterParser();
				else if ("comma:".equalsIgnoreCase(delimeterType))
					return new CommaDelimeterParser();
			}
		}
		return null;
	}

}

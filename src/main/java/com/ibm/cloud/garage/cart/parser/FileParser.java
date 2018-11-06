package com.ibm.cloud.garage.cart.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.ibm.cloud.garage.cart.LineItem;

public interface FileParser {

	public List<LineItem> parseFile(File file) throws FileNotFoundException;
}

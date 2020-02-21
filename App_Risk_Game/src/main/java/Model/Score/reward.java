package yash;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

pulic class Test {


public void validateFileFormatTest() throws FileNotFoundException {
	String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + "map";
	File file = new File(filePath);
;
	flag = mfv.validateFile(file);
	assertEquals(true, flag);
}

public void add() throws FileNotFoundException {
	fileLoadTest();
	String result = addContinent(continents, "Asiaa", "7");
	assertEquals("Continent added successfully", result);
}

public void remove() throws FileNotFoundException {
	fileLoadTest();
	String result = removeContinent(continents, boundries, countries, "Europe");
	assertEquals("Continents and countries under continent removed successfully", result);
}
}
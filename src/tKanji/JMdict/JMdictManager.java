package tKanji.JMdict;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class JMdictManager {
	public static void toDict(String inputDict,String filename) {

		try {
			File inputFile = new File(inputDict);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			JMdictEntryHandler handler = new JMdictEntryHandler();
			saxParser.parse(inputFile, handler);
			Files.write(Paths.get(filename), handler.toString().getBytes("UTF8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
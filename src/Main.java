package CSC_346_Group3.src;

import CSC_346_Group3.src.ParseHandler;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        File xmlFile = new File("test_xml_folder/states-updated.xml");
        // You can also try these example files if you want:
        // File xmlFile = new File("test_xml_folder/woz.xml");
        // File xmlFile = new File("test_xml_folder/weather.xml");
        // File xmlFile = new File("test_xml_folder/states.xml");   - this one has some syntax errors
        ParseHandler handler = new ParseHandler();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFile, handler);

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
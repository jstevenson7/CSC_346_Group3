package CSC_346_Group3.src;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;

public class Main {
    public static void main(String[] args) {
        File xmlFile = new File("CSC_346_Group3/test_xml_folder/states.xml");
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
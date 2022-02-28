package CSC_346_Group3.src;

import java.util.ArrayList;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * To use the SAX package, create a class that extends DefaultHandler. This will
 * contain the methods that are called when XML attributes are identified by the
 * parser.
 * You will then instantiate this class and pass it to a SAXParser's parse()
 * method alongside a File object corresponding to the parsed XML file.
 * This parse method will call your object's methods when it encounters various
 * XML attributes. It will read the file sequentially.The advantage of this
 * package
 * is that it allows you to decide how data should be stored. This allows for
 * greater
 * space efficiency than a package that, for example, just loads the entire XML
 * file into memory so that you can whittle away unneccessary tags until you are
 * left
 * with the extracted data.
 */
public class ParseHandler extends DefaultHandler {

    ArrayList<String> errorStrings;

    @Override
    public void startDocument() {
        System.out.println("start of the document: ");
        errorStrings = new ArrayList<String>();
    }

    // These methods are called by the parsers with the content that it discovers
    // in the XML file. These values are not stored unless YOU decide that they are useful.

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String[] elements = { qName };
        for (String element : elements) {
            System.out.println(element);
        }
        // attribute parsing is thankfully easy with the SAX parser
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println(attributes.getValue(i));
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        for (int i = start; i < start + length; i++) {
            if (ch[i] != '\n' && ch[i] != '\t' && ch[i] != ' ') {
                System.out.print(ch[i]);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String[] elements = { qName };
        for (String element : elements) {
            System.out.println(element);
        }
    }

    // You could, for example, save the errors/warnings in a temporary list, and
    // then print them out at the end.
    // This way, you could better separate the errors from the output.

    @Override
    public void warning(SAXParseException e) throws SAXException {
        errorStrings.add(e.toString());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        errorStrings.add(e.toString());
    }

    // Fatal errors are fatal. There are certain syntax errors that must be fixed
    // for the parser to work.
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("\n\nFATAL ERROR AT LINE " + e.getLineNumber() + ": \n" + e.getMessage());
    }

    @Override
    public void endDocument() {
        System.out.println("end of the document: ");
        System.out.println("Errors: ");
        for (String i : errorStrings) {
            System.out.println(i);
        }
    }
}

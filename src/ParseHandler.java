package CSC_346_Group3.src;

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

    @Override
    public void startDocument() {
        System.out.println("start of the document: ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String[] elements = {qName};
        for (String element : elements) {
            try {
                System.out.println(element);
            } catch (Exception e) {
                System.out.println("ERROR IN PARSING ELEMENT START!");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            try {
                System.out.println(attributes.getValue(i));
            } catch (Exception e) {
                System.out.println("ERROR IN PARSING ATTRIBUTE!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        for (int i = start; i < start + length; i++) {
            try {
                if (ch[i] != '\n' && ch[i] != '\t' && ch[i] != ' ') {
                    System.out.print(ch[i]);
                }
            } catch (Exception e) {
                System.out.println("ERROR IN PARSING CHAR!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String[] elements = {qName};
        for (String element : elements) {
            try {
                System.out.println(element);
            } catch (Exception e) {
                System.out.println("ERROR IN PARSING ELEMENT END!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endDocument() {
        System.out.println("end of the document: ");
    }
}

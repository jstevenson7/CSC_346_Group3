# SAX Parser Tutorial for Java

## Defining the *ParseHandler* class
To use the SAX package, create a class that extends DefaultHandler. 
This will contain the methods that are called when XML attributes are identified by the parser. 

The first method is the **startDocument()** method. This is always called before SAX begins parsing. There's not much to put in this method. The main use case is to verify that the parser has successfully started. You CAN initialize fields that you will later use to store data from the parse, but you can also just put that in a constructor:
```Java
    @Override
    public void startDocument() {
        System.out.println("start of the document: ");
        errorStrings = new ArrayList<String>();
    }
```

Next is the **startElement()** method. The parser calls this when it encounters an opening tag or "start tag". This method is called with all of the necessary data. By all necessary data, we really mean ALL of it. Not only does the SAX parser support Attributes, but also namespace URI's. Ultimately though, you can choose to make your implementation as basic or convoluted as you need to. In our example, we print the element name, and all attributes (if there are any): 
```Java
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println(qName);
        
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println(attributes.getValue(i));
        }
    }
```

Next, we have the **characters()** method, which is responsible for printing out the actual text between the element tags. Pretty simple, right? You may be thinking, why do I need `start` and `length`? Surely we can just iterate over each character in ch[]? Yeah, don't do that. If you don't iterate over the range specified, you can get all sorts of garbage in your result (mainly bits and pieces of enclosing tags and attributes).
```Java
    @Override
    public void characters(char ch[], int start, int length) {
        for (int i = start; i < start + length; i++) {
            if (ch[i] != '\n' && ch[i] != '\t' && ch[i] != ' ') {
                System.out.print(ch[i]);
            }
        }
    }
```

Next, **endElement()**. This is very similar to **startElement**, minus the attributes, since closing elements don't have them.
```Java
    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println(qName);
    }
```

Finally, **endDocument** is called, you guessed it, at the end of the document. You can use this method to put any finishing touches on the data that you parsed in the previous methods. In our example, we simply print "end of the document", and then print any errors we encountered during the parse:
```Java
    @Override
    public void endDocument() {
        System.out.println("end of the document: ");
        System.out.println("Errors: ");
        for (String i : errorStrings) {
            System.out.println(i);
        }
    }
```

> :bulb: **Tip:** If you're having trouble tracking the parser add some extra output  to the "*System.out.println(element);*" line, such as  "*System.out.println("This is an end element " + element);"*

### Error Handling
Error handing in the SAXParser is done by overriding individual error calls, in our example we add these errors to an array
and handle them at the end. These are our error overrides.
```Java
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
```

## Calling the parser in *main()*
Once you have defined the needed functionality in ParseHandler (or whatever you chose to call it), you will need to create an instance. In the *main()* method you will instantiate it alongside a SAXParser object (which you can get using a SAXParserFactory). Pass your handler to the SAXParser's *parse()* method alongside a File object corresponding to XML file to be parsed. 
```Java
        File xmlFile = new File("test_xml_folder/weather.xml");
        ParseHandler handler = new ParseHandler();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFile, handler);

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
```
This parse method will call your object's methods when it encounters various XML attributes and read the file sequentially.

The advantage of this package is that it allows you to decide how data should be stored. This allows for greater space efficiency than a package that, for example, just loads the entire XML file into memory so that you can whittle away unnecessary tags until you are left with the extracted data.

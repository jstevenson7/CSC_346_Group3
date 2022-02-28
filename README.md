# SAX Parser Tutorial for Java

## Defining the *ParseHandler* class
To use the SAX package, create a class that extends DefaultHandler. 
This will contain the methods that are called when XML attributes are identified by the parser. 

The first method is the **startDocument()** method which should look like this:
```Java
    @Override
    public void startDocument() {
        System.out.println("start of the document: ");
    }
```

Next is the **startElement()** method, this pulls and prints the start of each element out of the file and should look similar to this: 
```Java
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
                System.out.println("ERROR IN PARSING ELEMENT START!");
                e.printStackTrace();
            }
        }
    }
```

After **startElement()** is the **characters()** method. This method is responsible for printing out the actual text in between the element tags. 
It is similar to **startElement()** in that it uses a for loop to iterate through the file, but pulls out characters instead of *element*.
The following code also uses the boolean *isRealString* to check if each character is not a line break or empty space before printing.
```Java
    @Override
    public void characters(char ch[], int start, int length) {
        boolean isRealString = false;
        for (int i = start; i < start + length; i++) {
            try {
                if (ch[i] != '\n' && ch[i] != '\t' && ch[i] != ' ') {
                    System.out.print(ch[i]);
                    isRealString = true;
                }
            } catch (Exception e) {
                System.out.println("ERROR IN PARSING CHAR!");
                e.printStackTrace();
            }
        }
        if (isRealString) {
            System.out.println();
        }
    }
```

After the **characters()** method is **endElement()** which is the same as the first *for* loop from the  **startElement()** method and looks 
like this:
```Java
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
```

> :bulb: **Tip:** If you're having trouble tracking the parser add some extra output  to the "*System.out.println(element);*" line, such as  "*System.out.println("This is an end element " + element);"*

After **endElement()** the only thing left to do is signal the end of the document with **endDocument()** as follows:
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

### Error Handling
Error handing in the SAXParser is done by overriding individual error calls, in our example we will add these errors to an array
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
In the *main()* method you will instantiate this class and pass it to a SAXParser's *parse()* method alongside a File object corresponding to XML file to be parsed. 
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

The advantage of this package is that it allows you to decide how data should be stored. 
This allows for greater space efficiency than a package that, for example, 
just loads the entire XML file into memory so that you can whittle away unnecessary tags until you are left with the extracted data.

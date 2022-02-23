# SAX Parser Tutorial for Java
To use the SAX package, create a class that extends DefaultHandler. This will contain the methods that are called when XML attributes are identified by the parser. You will then instantiate this class and pass it to a SAXParser's parse() method alongside a File object corresponding to the parsed XML file. This parse method will call your object's methods when it encounters various XML attributes. It will read the file sequentially. The advantage of this package is that it allows you to decide how data should be stored. This allows for greater space efficiency than a package that, for example, just loads the entire XML file into memory so that you can whittle away unneccessary tags until you are left with the extracted data.

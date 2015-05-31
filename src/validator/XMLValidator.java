
package validator;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;

public class XMLValidator {

    private XMLValidator() {
    }
    
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    
    public static void validate(String xmlSource, String...xsd) throws SAXNotSupportedException, SAXException, IOException, ParserConfigurationException {

        String filename = xmlSource;
        
        String[] schemas = xsd;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://xml.org/sax/features/validation", true);
        factory.setFeature("http://apache.org/xml/features/validation/schema", true);
        factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
       
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", schemas);
        DocumentBuilder builder = factory.newDocumentBuilder();
        MyErrorHandler meh = new MyErrorHandler("log.txt");
        builder.setErrorHandler(meh);
        

        builder.parse(filename); 
        
        if(meh.isValid()) {
            System.out.println("Document is valid!");
        }
    } 
}
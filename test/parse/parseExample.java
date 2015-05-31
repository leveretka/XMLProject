/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.jdom2.JDOMException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import validator.XMLValidator;
import xmlproject.DOMParser;
import xmlproject.SAXParser;
import xmlproject.StaxParser;
import validator.Transformer;

/**
 *
 * @author margarita
 */
public class parseExample {
    
    public parseExample() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() throws SAXException, SAXNotSupportedException, IOException, ParserConfigurationException {
    
        try {
            SAXParser parser = new SAXParser();
            parser.parse();
            System.out.println("------------------------------------------------------------------");
            DOMParser domp = new DOMParser();
            domp.parse("gems.xml");
            domp.show();
            Transformer.transform("gems.xsl", "gems.xml", "gems.html");
            System.out.println("------------------------------------------------------------------");

            StaxParser staxParser = new StaxParser();
            staxParser.parse();
            System.out.println("------------------------------------------------------------------");
            
            XMLValidator.validate("gems.xml", "gems.xsd", "parameters.xsd");
        } catch (JDOMException | IOException | XMLStreamException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(parseExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

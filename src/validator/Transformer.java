/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author margarita
 */
public class Transformer {
    public static void transform(String xslFileName, String xmlFileName, String target) {
        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tFact.newTransformer(new StreamSource(xslFileName));
            transformer.transform(new StreamSource(xmlFileName), new StreamResult(target));
        } catch (TransformerException e){ 
            e.printStackTrace(); 
        }    

    }
}

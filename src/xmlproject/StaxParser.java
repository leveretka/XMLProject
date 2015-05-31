/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproject;

import gem.Gem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.Attributes;
import gem.Gem.Tag;
import gem.GemsContainer;

/**
 *
 * @author margarita
 */


public class StaxParser {

    private Gem.Tag curTag = Gem.Tag.NONE;
    private List<Gem> gemList = new ArrayList<>();
    private Gem g = null;
    private String tagContent = null;


    public void parse() throws XMLStreamException, FileNotFoundException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream("gems.xml");
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

        while(reader.hasNext()) {
            
            int event = reader.next();
            switch (event) {
                
                case XMLStreamConstants.START_ELEMENT:
                    curTag = Tag.valueOf(reader.getLocalName().toUpperCase());
                    startElement(reader.getAttributeValue(0));
                    break;
                    
                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    characters();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    curTag = Tag.valueOf(reader.getLocalName().toUpperCase());
                    endElement();
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    gemList = new ArrayList<>();
                    break;
            }

        }

        for (Gem gem: gemList) {
            System.out.println(gem);
        }

    }

    public GemsContainer getGems() {
        return new GemsContainer(gemList);
    }
    
    public void startDocument() {
        System.out.println("start parsing...");
    }
    
    public void endDocument() {
        System.out.println("...end parsing");
    }
    
    public void startElement(String...attr) {
        switch(curTag) {
            case GEM :
                g = new Gem();
                g.setId(attr[0]);
                break;
            
            case PARAMETERS :
                g.setParameters(new Gem.VisualParameters());
                break;
        }
    }

    public void characters() {

        Gem.VisualParameters vp;
        
        switch(curTag) {
            case NAME :
                g.setName(tagContent);
                break;
            
            case PRECIOUSNESS: 
                g.setPreciousness(Gem.Preciousness.valueOf(tagContent.toUpperCase()));
                break;
            
            case ORIGIN: 
                g.setOrigin(tagContent);
                break;
            
            case COLOR: 
                vp = g.getParameters();
                vp.setColor(Gem.VisualParameters.Color.valueOf(tagContent.toUpperCase()));
                g.setParameters(vp);
                break;
  
            case OPACITY: 
                vp = g.getParameters();
                vp.setOpacity(Integer.parseInt(tagContent));
                g.setParameters(vp);
                break;
            
            case VERGES: 
                vp = g.getParameters();
                vp.setVerges(Integer.parseInt(tagContent));
                g.setParameters(vp);
                break;
            
            case VALUE: 
                g.setValue(Double.parseDouble(tagContent));
                break;
            
        }
    }

    public void endElement() {
        switch(curTag) {
            case GEM:
                gemList.add(g);
                g = null;
                curTag = Tag.NONE;
                break;
            
        }
        curTag = Tag.NONE;
    }


}
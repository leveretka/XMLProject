/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproject;

import gem.Gem;
import gem.GemsContainer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static gem.Gem.Tag;
import static gem.Gem.VisualParameters;
/**
 *
 * @author margarita
 */
class SAXHandler extends DefaultHandler{
    private List<Gem> l  = new ArrayList<>();
    private Gem g;
    private Tag curTag = Tag.NONE;
    
    public List<Gem> getGems() {
        return l;
    }
    
    @Override
    public void startDocument() {
        System.out.println("start parsing...");
    }
    
    @Override
    public void endDocument() {
        System.out.println("...end parsing");
    }
    
    private String getLocal(String s) {
        int pos = s.lastIndexOf(":");
        if (pos >= 0) {
            s = s.substring(pos + 1);
        }
        return s;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String name = getLocal(qName);
        curTag = Tag.valueOf(name.toUpperCase());

        switch(curTag) {
            case GEM :
                g = new Gem();
                g.setId(attrs.getValue("id"));
                break;
            
            case PARAMETERS :
                g.setParameters(new Gem.VisualParameters());
                break;
        }
    }
    
    @Override
    public void characters(char[] text, int start, int len) {
        String s = new String(text, start, len);
        VisualParameters vp;
        
        switch(curTag) {
            case NAME :
                g.setName(s);
                break;
            
            case PRECIOUSNESS: 
                g.setPreciousness(Gem.Preciousness.valueOf(s.toUpperCase()));
                break;
            
            case ORIGIN: 
                g.setOrigin(s);
                break;
            
            case COLOR: 
                vp = g.getParameters();
                vp.setColor(Gem.VisualParameters.Color.valueOf(s.toUpperCase()));
                g.setParameters(vp);
                break;
  
            case OPACITY: 
                vp = g.getParameters();
                vp.setOpacity(Integer.parseInt(s));
                g.setParameters(vp);
                break;
            
            case VERGES: 
                vp = g.getParameters();
                vp.setVerges(Integer.parseInt(s));
                g.setParameters(vp);
                break;
            
            case VALUE: 
                g.setValue(Double.parseDouble(s));
                break;
            
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) {
        String name = getLocal(qName);
        curTag = Tag.valueOf(name.toUpperCase());
        switch(curTag) {
            case GEM:
                l.add(g);
                g = null;
                break;
            
        }
        curTag = Tag.NONE;
    }

}

public class SAXParser {
    
    public GemsContainer parse() throws ParserConfigurationException, SAXException, IOException {
        javax.xml.parsers.SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        SAXHandler gems  = new SAXHandler();
        parser.parse("gems.xml", gems);
        
        GemsContainer listGems = new GemsContainer(gems.getGems());
        listGems.sort();
        for(Gem g: listGems) {
            System.out.println(g);
        }
        return listGems;
    }

}

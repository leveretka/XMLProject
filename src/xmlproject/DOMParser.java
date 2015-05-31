/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproject;

import gem.Gem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;
import org.jdom2.Namespace;
import static gem.Gem.VisualParameters;
import static gem.Gem.Preciousness;
import gem.GemsContainer;
/**
 *
 * @author margarita
 */
public class DOMParser {
    private List<Gem> l;
    
    private VisualParameters parseVP(Element elem) {

        Namespace ns = Namespace.getNamespace("http://aaa.com/visual");
        
        VisualParameters parameters = new Gem.VisualParameters();
            
        parameters.setColor(Gem.VisualParameters.Color.valueOf(elem.getChild("color", ns)
                .getValue()
                .toUpperCase()));
        parameters.setOpacity(Integer.parseInt(elem.getChild("opacity", ns)
                .getText()));
        parameters.setVerges(Integer.parseInt(elem.getChild("verges", ns)
                .getText()));
        
        return parameters;
    }
    
    private Gem parseGem(Element gem) {
        
        Gem g = new Gem();
        
        g.setId(gem.getAttributeValue("id"));
        g.setName(gem.getChild("name").getValue());
        g.setPreciousness(Gem.Preciousness.valueOf(gem.getChild("preciousness")
                .getValue()
                .toUpperCase()));
        
        g.setOrigin(gem.getChild("origin").getValue());
        g.setValue(Double.parseDouble(gem.getChild("value").getValue()));
            
        Element elem = gem.getChild("parameters");
            
        g.setParameters(parseVP(elem));
        
        return g;
                
    }
    
    public void parse(String filename) throws JDOMException, IOException {
        
        SAXBuilder builder = new SAXBuilder();
	File xmlFile = new File(filename);
        
        l = new ArrayList<>();
        Document doc = builder.build(xmlFile);
        Element root = doc.getRootElement();
        
        List<Element> list = root.getChildren("gem");
        for (Element el: list) {
            l.add(parseGem(el));
           
        }
        
    }
    
    public void show() {

        if (l == null) {
            return;
        }
        
        for (Gem g: l) {
            System.out.println(g);
        }
    }
    
    public GemsContainer getGems() {
        return new GemsContainer(l);
    }

}

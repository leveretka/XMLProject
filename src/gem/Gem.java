
package gem;

import java.util.Comparator;

/**
 *
 * @author margarita
 */
public class Gem {

    public static enum Preciousness{PRECIOUS, SEMIPRECIOUS}
    public static enum Tag{NONE, VALUE, GEM, GEMS, NAME, PRECIOUSNESS, ORIGIN, PARAMETERS, COLOR, OPACITY, VERGES}
    
    private String id;
    private String name;
    private Preciousness preciousness;
    private String origin;
    private double value;
    private VisualParameters parameters;
            
    public Gem() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    
    /**
     *
     * @param parameters
     */
    public void setParameters(VisualParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Gem{" + "id=" + id + ", name=" + name + ", preciousness=" + preciousness + ", origin=" + origin + ", value=" + value + ", parameters=" + parameters + '}';
    }

    public double getValue() {
        return value;
    }
    
    public static class VisualParameters {
        
        public static enum Color{TRANSPARENT, RED, GREEN, BLUE, CYAN, YELLOW, PINK}
        
        private Color color;
        private int opacity;
        private int verges;

        public Color getColor() {
            return color;
        }

        public int getOpacity() {
            return opacity;
        }

        public int getVerges() {
            return verges;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setOpacity(int opacity) {
            this.opacity = opacity;
        }

        public void setVerges(int verges) {
            this.verges = verges;
        }

        @Override
        public String toString() {
            return "VisualParameters{" + "color=" + color + ", opacity=" + opacity + ", verges=" + verges + '}';
        }
        
        
    }
}

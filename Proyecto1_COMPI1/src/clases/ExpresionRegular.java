
package clases;

public class ExpresionRegular {
    public String nombreVariable;
    public String[] expresionRegular;
    
    public ExpresionRegular (String nombreVariable, String[] expresionRegular) {
        this.nombreVariable = nombreVariable;
        this.expresionRegular = expresionRegular;
    }
    
    public ExpresionRegular () {
        this.nombreVariable = "";
        this.expresionRegular = null;
    }
}


package clases;


public class Conjunto {
    public String nombreVariable;
    public String []rango;
   
    public Conjunto(String nombreVariable, String []rango){
        this.nombreVariable = nombreVariable;
        this.rango = rango;
    }
    
    public Conjunto() {
        this.nombreVariable = "";
        this.rango = null;
    }
   
}

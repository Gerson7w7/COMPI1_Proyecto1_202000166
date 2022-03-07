
package clases;


public class Conjunto {
    public String nombreVariable;
    public String []rango;
    public boolean esRango; 
   
    public Conjunto(String nombreVariable, String []rango, boolean esRango){
        this.nombreVariable = nombreVariable;
        this.rango = rango;
        this.esRango = esRango;
    }
    
    public Conjunto() {
        this.nombreVariable = "";
        this.rango = null;
        this.esRango = false;
    }
   
}

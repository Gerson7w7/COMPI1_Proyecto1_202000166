
package clases;

public class RegExpEvaluar {
    public String nombreVariable;
    public String cadena;
    public boolean esAceptado;
    
    public RegExpEvaluar(String nombreVariable, String cadena, boolean esAceptado){
            this.nombreVariable = nombreVariable;
            this.cadena = cadena;
            this.esAceptado = esAceptado;
    }
    
    public RegExpEvaluar(){
            this.nombreVariable = "";
            this.cadena = "";
            this.esAceptado = false;
    }
}

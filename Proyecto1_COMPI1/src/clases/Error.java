
package clases;


public class Error {
    public String tipo;
    public String descripcion;
    public int linea;
    public int columna;
    
    public Error(String tipo, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }
}

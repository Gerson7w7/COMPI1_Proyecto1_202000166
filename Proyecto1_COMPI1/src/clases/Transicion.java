
package clases;

import java.util.ArrayList;

public class Transicion {
    
    boolean esAceptacion;
    String estado;
    ArrayList<Integer> contenido;  
    ArrayList<Hoja> transiciones;
    
    public Transicion(String estado, ArrayList<Integer> contenido, Siguiente[] tablaSiguientes) {
        this.esAceptacion = false;
        this.estado = estado;
        this.contenido = contenido;       
        initTransiciones(tablaSiguientes);
        System.out.println("");
    }
    
    final void initTransiciones(Siguiente[] tablaSiguientes) {       
        boolean yaEsta;
        for(Siguiente siguiente : tablaSiguientes) {
            yaEsta = false;
            if(this.transiciones != null) {
                for(Hoja hoja : this.transiciones) {
                    if(hoja.nombre.equals(siguiente.hoja)) {
                        yaEsta = true;
                        hoja.identificador.add(siguiente.identificador);
                    } else if(siguiente.hoja.equals("#")) {
                        yaEsta = true;
                    }
                }
                if(!yaEsta) {
                    this.transiciones.add(new Hoja(siguiente.hoja, siguiente.identificador));   
                }
            } else {
                this.transiciones = new ArrayList<>();
                this.transiciones.add(new Hoja(siguiente.hoja, siguiente.identificador));   
            }
        }
    }
}

class Hoja {
    String nombre;
    ArrayList<Integer> identificador;
    ArrayList<Integer> contenido;
    String estado;
    
    public Hoja(String nombre, int identificador) {
        this.nombre = nombre;
        this.identificador = new ArrayList();
        this.identificador.add(identificador);
        this.contenido = null;
        this.estado = "";
    }
}

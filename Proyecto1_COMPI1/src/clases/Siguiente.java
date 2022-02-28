
package clases;

import java.util.ArrayList;

public class Siguiente {

    String hoja;
    int identificador;
    ArrayList<Integer> siguientes;
    
    public Siguiente(String hoja, int identificador) {
        this.hoja = hoja;
        this.identificador = identificador;
        this.siguientes = null;
    }
}

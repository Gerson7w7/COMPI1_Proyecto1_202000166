
package clases;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AFN {
    NodoAFN inicio;
    // estas estructuras nos ayudarán a armar el autómata
    Stack<String> hojas;
    Queue<NodoAFN[]> conjuntos;
    int contador = 1;
    boolean fueOperacion = false;
    
    public AFN() {
        this.inicio = null;
        this.hojas = new Stack();
        this.conjuntos = new LinkedList();
    }    
    
    // método para recorrer el arbol ast postorder
    public void recorridoAST(Nodo aux) {
        if(aux != null){          
            this.recorridoAST(aux.izquierda);
            this.recorridoAST(aux.derecha);
            add(aux);
        }
    }
    
    // aqui agregaremos los nodos del afn
    public void add(Nodo nodo) {
        System.out.println(nodo.dato);
        if(nodo.tipo.equals("hoja")) {
            // agregando a la pila las hojas
            this.hojas.push(nodo.dato);
        } else if(nodo.dato.equals("+")) {
            
        } else if(nodo.dato.equals("?")) {
            
        } else if(nodo.dato.equals("*")) {
            
        } else if(nodo.dato.equals(".")) {
            
            
        } else if(nodo.dato.equals("|")) {
            // comprobando si la pila está vacía
            if(!this.hojas.empty()) {                
                // obtenemos la hoja que esta en el top
                String hoja1 = this.hojas.pop();
                // como es un or, tenemos que sacar 2 hojas o 1 hoja y un conjunto
                if(!this.hojas.empty()) {
                    String hoja2 = this.hojas.pop();
                    // construimos el conjunto y lo añadimos a la cola de conjuntos
                    NodoAFN nodo1 = new NodoAFN("S" + contador, "£", true);
                    this.contador++;
                    NodoAFN nodo2 = new NodoAFN("S" + contador, "£", true);
                    this.contador++;
                    NodoAFN nodo3 = new NodoAFN("S" + contador, "£", true);
                    this.contador++;                  
                    NodoAFN nodo4 = new NodoAFN("S" + contador, hoja1, false);
                    this.contador++;
                    NodoAFN nodo5 = new NodoAFN("S" + contador, hoja2, false);
                    this.contador++;
                    NodoAFN nodo6 = new NodoAFN("S" + contador, "£", true);
                    this.contador++;
                    // enlazando
                    nodo5.estadoSiguiente1 = nodo6;
                    nodo4.estadoSiguiente1 = nodo6;
                    nodo3.estadoSiguiente1 = nodo5;
                    nodo2.estadoSiguiente1 = nodo4;
                    nodo1.estadoSiguiente1 = nodo3;
                    nodo1.estadoSiguiente2 = nodo2;
                    NodoAFN[] aux = {nodo1, nodo6};
                    this.conjuntos.add(aux);
                } else {
                    // construimos el conjunto y lo añadimos a la cola de conjuntos
                    NodoAFN nodo1 = new NodoAFN("S" + contador, "£", true);
                    this.contador++;
                    NodoAFN nodo2 = new NodoAFN("S" + contador, "£", true);
                    this.contador++;
                    NodoAFN nodo3 = new NodoAFN("S" + contador, hoja1, true);
                    this.contador++;  
                    NodoAFN nodo4 = new NodoAFN("S" + contador, "£", false);
                    this.contador++;
                    // enlazando                    
                    NodoAFN[] conjunto = this.conjuntos.poll();
                    conjunto[1].estadoSiguiente1 = nodo4;
                    nodo3.estadoSiguiente1 = nodo4;
                    nodo2.estadoSiguiente1 = nodo3;
                    nodo1.estadoSiguiente1 = conjunto[0];
                    nodo1.estadoSiguiente2 = nodo2;
                    NodoAFN[] aux = {nodo1, nodo4};
                    this.conjuntos.add(aux);
                }
            } else {
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo1 = new NodoAFN("S" + contador, "£", true);
                this.contador++;
                NodoAFN nodo2 = new NodoAFN("S" + contador, "£", true);
                this.contador++; 
                // enlazando                    
                NodoAFN[] conjunto1 = this.conjuntos.poll();
                NodoAFN[] conjunto2 = this.conjuntos.poll();
                conjunto1[1].estadoSiguiente1 = nodo2;
                conjunto2[1].estadoSiguiente1 = nodo2;
                nodo1.estadoSiguiente1 = conjunto1[0];
                nodo1.estadoSiguiente2 = conjunto2[0];
                NodoAFN[] aux = {nodo1, nodo2};
                this.conjuntos.add(aux);
            }
        }
    }
}

class NodoAFN {
    String estado;
    String llegaronCon;
    boolean esEpsilon;
    NodoAFN estadoSiguiente1;
    NodoAFN estadoSiguiente2;
    
    public NodoAFN(String estado, String llegaronCon, boolean esEpsilon) {
        this.estado = estado;
        this.llegaronCon = llegaronCon;
        this.esEpsilon = esEpsilon;
        this.estadoSiguiente1 = null;
        this.estadoSiguiente2 = null;
    }
}

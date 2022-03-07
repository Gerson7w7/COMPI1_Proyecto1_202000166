package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class AFN {

    String nombreRegex;
    public NodoAFN inicio;
    // estas estructuras nos ayudarán a armar el autómata
    public Stack<String> hojas;
    public Stack<NodoAFN[]> conjuntos;
    int contador = 1;

    public AFN(String nombreRegex) {
        this.nombreRegex = nombreRegex;
        this.inicio = new NodoAFN("S0", "");
        this.hojas = new Stack();
        this.conjuntos = new Stack();
    }

    public void addInicio() {
        this.inicio.estadoSiguiente1 = this.conjuntos.pop()[0];
    }

    // método para recorrer el arbol ast postorder
    public void recorridoAST(Nodo aux) {
        if (aux != null) {
            this.recorridoAST(aux.izquierda);
            this.recorridoAST(aux.derecha);
            add(aux);
        }
    }

    // aqui agregaremos los nodos del afn
    public void add(Nodo nodo) {
        if (nodo.tipo.equals("hoja")) {
            // agregando a la pila las hojas
            this.hojas.push(nodo.dato);
        } else if (nodo.dato.equals("+")) {
            // si su hijo es una hoja lo enlazamos con la hoja
            if (nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos la hoja que esta en el top
                String hoja1 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo3 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                nodo3.estadoSiguiente1 = nodo2;
                nodo3.estadoSiguiente2 = nodo4;
                nodo2.estadoSiguiente1 = nodo3;
                NodoAFN[] aux = {nodo2, nodo4};
                this.conjuntos.push(aux);
                // sino lo enlazamos con un conjunto    
            } else {
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                NodoAFN[] conjunto = this.conjuntos.pop();
                conjunto[1].estadoSiguiente1 = nodo2;
                conjunto[1].estadoSiguiente2 = nodo4;
                nodo2.estadoSiguiente1 = conjunto[0];
                NodoAFN[] aux = {nodo2, nodo4};
                this.conjuntos.push(aux);
            }
        } else if (nodo.dato.equals("?")) {
            // si su hijo es una hoja lo enlazamos con la hoja
            if (nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos la hoja que esta en el top
                String hoja1 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo3 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                nodo3.estadoSiguiente2 = nodo4;
                nodo2.estadoSiguiente1 = nodo3;
                nodo1.estadoSiguiente1 = nodo2;
                nodo1.estadoSiguiente2 = nodo4;
                NodoAFN[] aux = {nodo1, nodo4};
                this.conjuntos.push(aux);
                // sino lo enlazamos con un conjunto    
            } else {
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                NodoAFN[] conjunto = this.conjuntos.pop();
                conjunto[1].estadoSiguiente2 = nodo4;
                nodo2.estadoSiguiente1 = conjunto[0];
                nodo1.estadoSiguiente1 = nodo2;
                nodo1.estadoSiguiente2 = nodo4;
                NodoAFN[] aux = {nodo1, nodo4};
                this.conjuntos.push(aux);
            }
        } else if (nodo.dato.equals("*")) {
            // si su hijo es una hoja lo enlazamos con la hoja
            if (nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos la hoja que esta en el top
                String hoja1 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo3 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                nodo3.estadoSiguiente1 = nodo2;
                nodo3.estadoSiguiente2 = nodo4;
                nodo2.estadoSiguiente1 = nodo3;
                nodo1.estadoSiguiente1 = nodo2;
                nodo1.estadoSiguiente2 = nodo4;
                NodoAFN[] aux = {nodo1, nodo4};
                this.conjuntos.push(aux);
                // sino lo enlazamos con un conjunto    
            } else {
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                NodoAFN[] conjunto = this.conjuntos.pop();
                conjunto[1].estadoSiguiente1 = nodo2;
                conjunto[1].estadoSiguiente2 = nodo4;
                nodo2.estadoSiguiente1 = conjunto[0];
                nodo1.estadoSiguiente1 = nodo2;
                nodo1.estadoSiguiente2 = nodo4;
                NodoAFN[] aux = {nodo1, nodo4};
                this.conjuntos.push(aux);
            }
        } else if (nodo.dato.equals(".")) {
            // si los 2 hijos son hojas se enlazan simple
            if (nodo.derecha.tipo.equals("hoja") && nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos las primeras 2 hojas que esta en el top
                String hoja1 = this.hojas.pop();
                String hoja2 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo2 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                NodoAFN nodo3 = new NodoAFN("S" + contador, hoja2);
                this.contador++;
                // enlazando
                nodo2.estadoSiguiente1 = nodo3;
                NodoAFN[] aux = {nodo2, nodo3};
                this.conjuntos.push(aux);
                // si el hijo derecho es hoja y el izquierdo es un conjunto, se enlazan
            } else if (nodo.derecha.tipo.equals("hoja") && !nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos la hoja que esta en el top
                String hoja1 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo2 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                // enlazando                    
                NodoAFN[] conjunto = this.conjuntos.pop();
                conjunto[1].estadoSiguiente1 = nodo2;
                NodoAFN[] aux = {conjunto[0], nodo2};
                this.conjuntos.push(aux);
                // si el hijo derecho es un conjunto y el izquierdo es una hoja, se enlazan
            } else if (!nodo.derecha.tipo.equals("hoja") && nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos la hoja que esta en el top
                String hoja1 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo2 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                // enlazando                    
                NodoAFN[] conjunto = this.conjuntos.pop();
                nodo2.estadoSiguiente1 = conjunto[0];
                NodoAFN[] aux = {nodo2, conjunto[1]};
                this.conjuntos.push(aux);
                // si los dos hijos no son hojas se enlazan
            } else if (!nodo.derecha.tipo.equals("hoja") && !nodo.izquierda.tipo.equals("hoja")) {
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                // enlazando                    
                NodoAFN[] conjunto1 = this.conjuntos.pop(); // mas a la derecha
                NodoAFN[] conjunto2 = this.conjuntos.pop(); // menos a la derecha
                conjunto2[1].estadoSiguiente1 = conjunto1[0];
                NodoAFN[] aux = {conjunto2[0], conjunto1[1]};
                this.conjuntos.push(aux);
            }
        } else if (nodo.dato.equals("|")) {
            // si los 2 hijos son hojas, se enlazan simle
            if (nodo.derecha.tipo.equals("hoja") && nodo.izquierda.tipo.equals("hoja")) {
                // obtenemos las primeras 2 hojas que esta en el top
                String hoja1 = this.hojas.pop();
                String hoja2 = this.hojas.pop();
                // construimos el conjunto y lo añadimos a la cola de conjuntos
                NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo3 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                NodoAFN nodo4 = new NodoAFN("S" + contador, hoja1);
                this.contador++;
                NodoAFN nodo5 = new NodoAFN("S" + contador, hoja2);
                this.contador++;
                NodoAFN nodo6 = new NodoAFN("S" + contador, "epsilon");
                this.contador++;
                // enlazando
                nodo5.estadoSiguiente1 = nodo6;
                nodo4.estadoSiguiente1 = nodo6;
                nodo3.estadoSiguiente1 = nodo5;
                nodo2.estadoSiguiente1 = nodo4;
                nodo1.estadoSiguiente1 = nodo3;
                nodo1.estadoSiguiente2 = nodo2;
                NodoAFN[] aux = {nodo1, nodo6};
                this.conjuntos.push(aux);
                // sino se enlaza un conjunto y una hoja
            } else {
                if (!this.hojas.isEmpty()) {
                    // obtenemos la hoja que esta en el top
                    String hoja1 = this.hojas.pop();
                    // construimos el conjunto y lo añadimos a la cola de conjuntos
                    NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                    this.contador++;
                    NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                    this.contador++;
                    NodoAFN nodo3 = new NodoAFN("S" + contador, hoja1);
                    this.contador++;
                    NodoAFN nodo4 = new NodoAFN("S" + contador, "epsilon");
                    this.contador++;
                    // enlazando                    
                    NodoAFN[] conjunto = this.conjuntos.pop();
                    conjunto[1].estadoSiguiente1 = nodo4;
                    nodo3.estadoSiguiente1 = nodo4;
                    nodo2.estadoSiguiente1 = nodo3;
                    nodo1.estadoSiguiente1 = conjunto[0];
                    nodo1.estadoSiguiente2 = nodo2;
                    NodoAFN[] aux = {nodo1, nodo4};
                    this.conjuntos.push(aux);
                } else {
                    // construimos el conjunto y lo añadimos a la cola de conjuntos
                    NodoAFN nodo1 = new NodoAFN("S" + contador, "epsilon");
                    this.contador++;
                    NodoAFN nodo2 = new NodoAFN("S" + contador, "epsilon");
                    this.contador++;
                    // enlazando                    
                    NodoAFN[] conjunto1 = this.conjuntos.pop();
                    NodoAFN[] conjunto2 = this.conjuntos.pop();
                    conjunto1[1].estadoSiguiente1 = nodo2;
                    conjunto2[1].estadoSiguiente1 = nodo2;
                    nodo1.estadoSiguiente1 = conjunto1[0];
                    nodo1.estadoSiguiente2 = conjunto2[0];
                    NodoAFN[] aux = {nodo1, nodo2};
                    this.conjuntos.push(aux);
                }
            }
        }
    }

    // ============= GRAPHVIZ ==============
    public void crearDot(String cadena, String ruta) {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(cadena);
            pw.close();
            fichero.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public void graphviz() {
        String cadena = "digraph afn {\n";
        cadena += "rankdir=LR\n";
        cadena += this.nodosGraphviz(this.inicio);
        cadena += "\n";
        cadena += this.enlazarGraphviz(this.inicio);
        cadena += "\n}";
        try {
            String ruta = "REPORTES/AFND_202000166/" + this.nombreRegex;
            this.crearDot(cadena, ruta + ".dot");
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot", "-Tpng", "-o", ruta + ".png", ruta + ".dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
        }
    }

    public String nodosGraphviz(NodoAFN aux) { //metodo preorden
        String nodos = "";
        if (aux != null && !aux.yaEsta1) {
            if (aux.llegaronCon.equals("#")) {
                nodos += aux.estado + "[shape=\"doublecircle\"];\n";
            } else {
                nodos += aux.estado + "[shape=\"circle\"]\n";
            }
            aux.yaEsta1 = true;
            nodos += this.nodosGraphviz(aux.estadoSiguiente1);
            nodos += this.nodosGraphviz(aux.estadoSiguiente2);
        }
        return nodos;
    }

    public String enlazarGraphviz(NodoAFN aux) {
        String cadena = "";
        if (aux != null && !aux.yaEsta2) {
            //validaciones
            if (aux.estadoSiguiente1 != null) {
                cadena += aux.estado + "->" + aux.estadoSiguiente1.estado + " [label=\"" + aux.estadoSiguiente1.llegaronCon + "\"]\n";
            }
            if (aux.estadoSiguiente2 != null) {
                cadena += aux.estado + "->" + aux.estadoSiguiente2.estado + " [label=\"" + aux.estadoSiguiente2.llegaronCon + "\"]\n";
            }
            aux.yaEsta2 = true;
            cadena += this.enlazarGraphviz(aux.estadoSiguiente1);
            cadena += this.enlazarGraphviz(aux.estadoSiguiente2);
        }
        return cadena;
    }
}

class NodoAFN {

    public String estado;
    public String llegaronCon;
    public boolean yaEsta1, yaEsta2;
    public NodoAFN estadoSiguiente1;
    public NodoAFN estadoSiguiente2;

    public NodoAFN(String estado, String llegaronCon) {
        this.estado = estado;
        this.llegaronCon = llegaronCon;
        this.estadoSiguiente1 = null;
        this.estadoSiguiente2 = null;
        this.yaEsta1 = false;
        this.yaEsta2 = false;
    }
}

package clases;

public class AST {

    public Nodo raiz;
    public String nombreRegex;
    public int id;

    public AST(String nombreRegex) {
        this.nombreRegex = nombreRegex;
        // inicializando el arbol siempre con un . - # que es la terminacion de la cadena
        this.raiz = new Nodo(".", "binario", 1);
        this.raiz.derecha = new Nodo("#", "hoja", 2);
        this.id = 3;
    }

    public void regex(String[] regex) {
        Nodo nuevo;
        // recibimos la cadena de la expresion regular (forma polaca)
        for (String i : regex) {
            // verificando si se trata de una hoja o no
            if (i.equals(".") || i.equals("|")) {
                nuevo = new Nodo(i, "binario", this.id);
            } else if (i.equals("*") || i.equals("+") || i.equals("?")) {
                nuevo = new Nodo(i, "unario", this.id);
            } else {
                nuevo = new Nodo(i, "hoja", this.id);
            }
            // añadiendo el nodo
            add(nuevo, this.raiz);
            this.id++;
        }
    }

    public void add(Nodo nuevo, Nodo aux) {
        // si es binario (+|.) puede tener hijos en ambos lados
        if (aux.tipo.equals("binario")) {
            // si está vacío la izquierda se agrega el nodo, sino sigue recorriendo
            if (aux.izquierda == null) {
                nuevo.estaAsignado = true;
                aux.izquierda = nuevo;
            } else {
                add(nuevo, aux.izquierda);
            }
            // si está vacío la derecha se agrega el nodo, sino sigue recorriendo
            if (!nuevo.estaAsignado) {
                if (aux.derecha == null) {
                    nuevo.estaAsignado = true;
                    aux.derecha = nuevo;
                } else {
                    add(nuevo, aux.derecha);
                }
            }
        // si es unario (+|*|?) solo tiene un hijo    
        } else if (aux.tipo.equals("unario")) {
            if (aux.izquierda == null) {
                nuevo.estaAsignado = true;
                aux.izquierda = nuevo;
            } else {
                add(nuevo, aux.izquierda);
            }
        }
    }
    
    // ============= RECORRIDOS =============
    public void anulables(Nodo aux){
        if(aux != null){           
            this.anulables(aux.izquierda);
            this.anulables(aux.derecha);
            if (aux.tipo == "hoja") {
                // si es hoja: es NO anulable   
                aux.esAnulable = false;
            } else if (aux.dato.equals("+")) {
                // si su hijo es anulable, es anulable, de lo contrario es NO anulable
                aux.esAnulable = aux.izquierda.esAnulable; 
            } else if (aux.dato.equals("*") || aux.dato.equals("?")) {
                // si es * o ?: es anulable siempre 
                aux.esAnulable = true;
            } else if (aux.dato.equals(".")) {
                // si el hijo izquierdo y derecho son anulables, es anulable de lo contrario es NO anulable
                aux.esAnulable = aux.izquierda.esAnulable && aux.derecha.esAnulable;
            } else if (aux.dato.equals("|")) {
                // si el hijo izquierdo o derecho es anulable, entonces es anulable sino es NO anulable
                aux.esAnulable = aux.izquierda.esAnulable || aux.derecha.esAnulable;
            }            
        }
    }
    
    public void primerosUltimos(Nodo aux){
        if(aux != null){           
            this.primerosUltimos(aux.izquierda);
            this.primerosUltimos(aux.derecha);
            if (aux.tipo == "hoja") {
                // se empezará a numerar los primeros y los últimos desde las hojas
                aux.primeros += this.id;
                this.id++;
            } else if (aux.dato.equals("+")) {
                
            } else if (aux.dato.equals("*") || aux.dato.equals("?")) {
                
            } else if (aux.dato.equals(".")) {
                
            } else if (aux.dato.equals("|")) {
                
            }               
        }
    }
    
    // ============= GRAPHVIZ ==============
    public void graphviz() {
        String cadena = "digraph arbol {\n";
        cadena += this.nodosGraphviz(this.raiz);
        cadena += "\n";
        cadena += this.enlazarGraphviz(this.raiz);
        cadena += "\n}";
        System.out.println(cadena);
    }

    public String nodosGraphviz(Nodo aux) { //metodo preorden
        String nodos = "";
        if (aux != null) {
            nodos += "n" + aux.id + "[label=\"" + aux.dato + "\"]\n";
            nodos += this.nodosGraphviz(aux.izquierda);
            nodos += this.nodosGraphviz(aux.derecha);
        }
        return nodos;
    }

    public String enlazarGraphviz(Nodo aux) {
        String cadena = "";
        if (aux != null) {
            cadena += this.enlazarGraphviz(aux.izquierda);
            cadena += this.enlazarGraphviz(aux.derecha);
            //validaciones
            if (aux.izquierda != null) {
                cadena += "n" + aux.id + "-> n" + aux.izquierda.id + "\n";
            }
            if (aux.derecha != null) {
                cadena += "n" + aux.id + "-> n" + aux.derecha.id + "\n";
            }
        }
        return cadena;
    }
}

class Nodo {

    int id;
    boolean estaAsignado, esAnulable;
    String dato, tipo, primeros, ultimos;
    Nodo izquierda, derecha;

    public Nodo(String dato, String tipo, int id) {
        this.dato = dato;
        this.tipo = tipo;
        this.id = id;
        this.izquierda = null;
        this.derecha = null;
        this.estaAsignado = false;
        this.esAnulable = false;
        this.primeros = "";
        this.ultimos = "";
    }

}

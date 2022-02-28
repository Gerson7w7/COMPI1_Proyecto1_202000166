package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AST {

    public Nodo raiz;
    public String nombreRegex;
    public int id;
    public Siguiente[] tablaSiguientes;
    public ArrayList<Transicion> tablaTransiciones;

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
    public void anulables(Nodo aux) {
        if (aux != null) {
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

    public void primerosUltimos(Nodo aux) {
        if (aux != null) {
            this.primerosUltimos(aux.izquierda);
            this.primerosUltimos(aux.derecha);
            if (aux.tipo == "hoja") {
                // se empezará a numerar los primeros y los últimos desde las hojas
                aux.primeros += this.id;
                aux.ultimos += this.id;
                this.id++;
            } else if (aux.dato.equals("+")) {
                // primeros izq
                aux.primeros = aux.izquierda.primeros;
                // ultimos izq
                aux.ultimos = aux.izquierda.ultimos;
            } else if (aux.dato.equals("*") || aux.dato.equals("?")) {
                // primeros izq
                aux.primeros = aux.izquierda.primeros;
                // ultimos izq
                aux.ultimos = aux.izquierda.ultimos;
            } else if (aux.dato.equals(".")) {
                // si anulable izq: primeros izq U primeros der, sino primeros izq
                if (aux.izquierda.esAnulable) {
                    aux.primeros = aux.izquierda.primeros + "," + aux.derecha.primeros;
                } else {
                    aux.primeros = aux.izquierda.primeros;
                }
                // si anulable der: ultimos izq U ultimos der, sino ultimo der
                if (aux.derecha.esAnulable) {
                    aux.ultimos = aux.izquierda.ultimos + "," + aux.derecha.ultimos;
                } else {
                    aux.ultimos = aux.derecha.ultimos;
                }
            } else if (aux.dato.equals("|")) {
                // primeros izq U primeros der
                aux.primeros += aux.izquierda.primeros + "," + aux.derecha.primeros;
                // ultimos izq U ultimos der
                aux.ultimos += aux.izquierda.ultimos + "," + aux.derecha.ultimos;
            }
        }
    }

    public void siguientes() {
        // para saber cuantas hojas tiene el arbol, iremos a ver a el identificador de la última hoja, que siempre será (#)
        int cantidadHojas = Integer.parseInt(this.raiz.derecha.primeros);
        this.tablaSiguientes = new Siguiente[cantidadHojas];
        // primero pondremos las hojas con sus identificadores
        recorridoSiguientes(this.raiz);
        // reporte de siguientes
        Html html = new Html();
        html.reportSiguientes(this.nombreRegex, this.tablaSiguientes);
    }

    public void recorridoSiguientes(Nodo aux) {
        if (aux != null) {
            this.recorridoSiguientes(aux.izquierda);
            this.recorridoSiguientes(aux.derecha);
            if (aux.tipo == "hoja") {
                // aquí creamos una instancia de un objeto de tipo Siguiente
                int identificador = Integer.parseInt(aux.primeros);
                this.tablaSiguientes[identificador - 1] = new Siguiente(aux.dato, identificador);
            } else if (aux.dato.equals("+") || aux.dato.equals("*")) {
                // los primeros del hijo izq son los siguientes de los últimos del hijo izq
                String aux1[] = aux.izquierda.ultimos.split(",");
                int auxUltimos[] = new int[aux1.length];
                for (int i = 0; i < aux1.length; i++) {
                    auxUltimos[i] = Integer.parseInt(aux1[i]);
                }
                String aux2[] = aux.izquierda.primeros.split(",");
                int auxPrimeros[] = new int[aux2.length];
                for (int i = 0; i < aux2.length; i++) {
                    auxPrimeros[i] = Integer.parseInt(aux2[i]);
                }
                // asignando los siguientes para + y *
                for (Siguiente siguiente : this.tablaSiguientes) {
                    if (siguiente != null) {
                        for (int ultimo : auxUltimos) {
                            if (siguiente.identificador == ultimo) {
                                for (int primero : auxPrimeros) {
                                    if (siguiente.siguientes != null) {
                                        if (!siguiente.siguientes.contains(primero)) {
                                            siguiente.siguientes.add(primero);
                                        }
                                    } else {
                                        siguiente.siguientes = new ArrayList();
                                        siguiente.siguientes.add(primero);
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (aux.dato.equals(".")) {
                // los primeros del hijo der son los siguientes de los últimos del hijo izq
                String aux1[] = aux.izquierda.ultimos.split(",");
                int auxUltimos[] = new int[aux1.length];
                for (int i = 0; i < aux1.length; i++) {
                    auxUltimos[i] = Integer.parseInt(aux1[i]);
                }
                String aux2[] = aux.derecha.primeros.split(",");
                int auxPrimeros[] = new int[aux2.length];
                for (int i = 0; i < aux2.length; i++) {
                    auxPrimeros[i] = Integer.parseInt(aux2[i]);
                }
                // asignando los siguientes para .
                for (Siguiente siguiente : this.tablaSiguientes) {
                    if (siguiente != null) {
                        for (int ultimo : auxUltimos) {
                            if (siguiente.identificador == ultimo) {
                                for (int primero : auxPrimeros) {
                                    if (siguiente.siguientes != null) {
                                        if (!siguiente.siguientes.contains(primero)) {
                                            siguiente.siguientes.add(primero);
                                        }
                                    } else {
                                        siguiente.siguientes = new ArrayList();
                                        siguiente.siguientes.add(primero);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void transiciones() {
        // inicializamos el array
        this.tablaTransiciones = new ArrayList<>();
        // añadimos el estado inicial que son los primeros de la raiz
        String[] aux = this.raiz.primeros.split(",");
        ArrayList<Integer> estadoInicial = new ArrayList<>();
        for (String aux1 : aux) {
            estadoInicial.add(Integer.parseInt(aux1));
        }
        this.tablaTransiciones.add(new Transicion("S0", estadoInicial, this.tablaSiguientes));
        recorridoTransiciones(this.tablaTransiciones.get(0));
        // reporte de transiciones
        Html html = new Html();
        html.reportTransicion(this.nombreRegex, this.tablaTransiciones);
    }

    public void recorridoTransiciones(Transicion transicion) {
        for (Siguiente siguiente : this.tablaSiguientes) {
            // si en contenido de la transicion contiene una hoja del siguiente, se guarda una nueva transicion
            if (transicion.contenido.contains(siguiente.identificador)) {
                for (Hoja hoja : transicion.transiciones) {
                    if (hoja.identificador.contains(siguiente.identificador) && transicion.contenido.contains(siguiente.identificador)) {
                        if (hoja.contenido != null) {
                            // si tiene contenido, lo analizamos
                            for (int i : siguiente.siguientes) {
                                if (!hoja.contenido.contains(i)) {
                                    hoja.contenido.add(i);
                                }
                            }
                        } else {
                            // si está vacío el contenido, le agregamos todo el contenido 
                            hoja.contenido = siguiente.siguientes;
                        }
                        break;
                    }
                }
            }
        }
        nuevaTransicion(transicion);
    }

    public void nuevaTransicion(Transicion transicion) {
        boolean nuevoEstado = false;
        ArrayList<Integer> contenido = null;
        // recorriendo cada transicion
        for (int i = 0; i < this.tablaTransiciones.size(); i++) {
            for (int j = 0; j < this.tablaTransiciones.size(); j++) {
                // recorriendo cada hoja de la transicion que acabamos de completar
                for (Hoja hoja : this.tablaTransiciones.get(j).transiciones) {
                    if (hoja.contenido != null) {
                        // guardaremos el contenido de la hoja y dependiendo si es un estado nuevo o no, lo usaremos o no
                        nuevoEstado = !this.tablaTransiciones.get(i).contenido.containsAll(hoja.contenido);
                        contenido = hoja.contenido;
                    }
                }
            }
        }
        // si hay un estado nuevo, crearemos una nueva transicion
        if (nuevoEstado) {
            int nEstado = this.tablaTransiciones.size();
            Transicion estado = new Transicion("S" + nEstado, contenido, this.tablaSiguientes);
            if (contenido.contains(this.tablaSiguientes[this.tablaSiguientes.length - 1].identificador)) {
                // verificamos si se trata de un estado de aceptacion
                estado.esAceptacion = true;
            }
            this.tablaTransiciones.add(estado);
            this.recorridoTransiciones(this.tablaTransiciones.get(this.tablaTransiciones.size() - 1));
        } else {
            // por último, ponemos el nombre de los estados de cada transicon
            for (Transicion t1 : this.tablaTransiciones) {
                for (Transicion t2 : this.tablaTransiciones) {
                    for (Hoja h : t2.transiciones) {
                        if (h.contenido != null) {
                            if (h.contenido.containsAll(t1.contenido)) {
                                h.estado = t1.estado;
                            }
                        }
                    }
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
        String cadena = "digraph arbol {\n";
        cadena += this.nodosGraphviz(this.raiz);
        cadena += "\n";
        cadena += this.enlazarGraphviz(this.raiz);
        cadena += "\n}";
        try {
            String ruta = "REPORTES/ARBOLES_202000166/" + this.nombreRegex;
            this.crearDot(cadena, ruta + ".dot");
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot", "-Tpng", "-o", ruta + ".png", ruta + ".dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
        }
    }

    public String nodosGraphviz(Nodo aux) { //metodo preorden
        String nodos = "";
        if (aux != null) {
            if (aux.dato.equals("|")) {
                nodos += "n" + aux.id + "[shape=\"record\" label=\"" + aux.primeros + "| or |" + aux.ultimos + "\"]\n";
            } else {
                nodos += "n" + aux.id + "[shape=\"record\" label=\"" + aux.primeros + "| " + aux.dato + " |" + aux.ultimos + "\"]\n";
            }
            nodos += this.nodosGraphviz(aux.izquierda);
            nodos += this.nodosGraphviz(aux.derecha);
        }
        return nodos;
    }

    public String enlazarGraphviz(Nodo aux) {
        String cadena = "", anulable;
        if (aux != null) {
            cadena += this.enlazarGraphviz(aux.izquierda);
            cadena += this.enlazarGraphviz(aux.derecha);
            //validaciones
            if (aux.izquierda != null) {
                anulable = aux.izquierda.esAnulable ? "A" : "N";
                cadena += "n" + aux.id + "-> n" + aux.izquierda.id + " [label=\"" + anulable + "\"]\n";
            }
            if (aux.derecha != null) {
                anulable = aux.derecha.esAnulable ? "A" : "N";
                cadena += "n" + aux.id + "-> n" + aux.derecha.id + " [label=\"" + anulable + "\"]\n";
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

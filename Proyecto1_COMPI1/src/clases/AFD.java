package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AFD {
    // aquí estarán todos los estados
    ArrayList<Estado> estados;
    String nombreRegex;
    
    public AFD(String nombreRegex) {
        this.nombreRegex = nombreRegex;
        this.estados = new ArrayList<>();
    }
    
    public void tablaTransiciones(ArrayList<Transicion> tablaTransiciones) {
        // primero agregamos todos los estados al array
        int id = 0;
        for(Transicion transicion : tablaTransiciones) {
            estados.add(new Estado(id, transicion.estado, transicion.esAceptacion));
            id++;
        }
        // ahora enlazamos cada trancisicion con un estado
        for(Estado estado : this.estados) {
            for(Transicion transicion : tablaTransiciones) {
                for(Hoja hoja : transicion.transiciones) {
                    if(estado.nombreEstado.equals(transicion.estado)) {
                        // si es el nombre del estado igual que la transicion añadimos dicha trasicion
                        estado.add(hoja, this.estados);
                    } else {
                        // si es diferente ahí termina la transciones de dicho estado y pasa la siguiente
                        break;
                    }
                }
            }
        }               
    }
    
    // función para añadir los conjuntos definidos
    public void introRegex(ArrayList<Conjunto> conjuntos) {
        for(Estado estado : this.estados) {
            for(TransicionEstado transicion : estado.transiciones) {
                for(Conjunto conjunto : conjuntos) {
                    if(transicion.nombreRegex.contains(conjunto.nombreVariable)) {
                        
                    } else if(transicion.nombreRegex.contains("\"")) {
                        
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
    
    // método para graficar con graphviz
    public void graphviz() {
        String cadena = "digraph automata {\n";
        cadena += "rankdir=LR\n";
        cadena += "ini [shape=\"none\" label=\"\"]\n";
        cadena += this.nodosGraphviz();
        cadena += "\n";
        cadena += this.enlazarGraphviz();
        cadena += "\n}";
        try {
            String ruta = "REPORTES/AFD_202000166/" + this.nombreRegex;
            this.crearDot(cadena, ruta + ".dot");
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot", "-Tpng", "-o", ruta + ".png", ruta + ".dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
        }
    }
    
    public String nodosGraphviz() {
        String cadena = "";
        for(Estado estado : this.estados) {
            if(estado.esAceptacion) {
               cadena += estado.nombreEstado + "[shape=\"doublecircle\"];\n"; 
            } else {
               cadena += estado.nombreEstado + "[shape=\"circle\"];\n"; 
            }            
        }
        return cadena;
    }
    
    public String enlazarGraphviz() {
        String cadena = "", repetido = "inicio";
        for(Estado estado : this.estados) {
            for(TransicionEstado transicion : estado.transiciones) {
                // si la transicion es hacia el mismo estado pero con otra hoja, solo los separamos por |
                if(repetido.equals(transicion.estadoDestino.nombreEstado)) {
                    cadena += "|" + transicion.nombreRegex;
                // si es el inicio del automata lo enlazamos con el nodo inicio
                } else if(repetido.equals("inicio")) {
                    cadena += "ini->" + estado.nombreEstado + ";\n";
                    cadena += estado.nombreEstado + "->" + transicion.estadoDestino.nombreEstado + "[label=\"" + transicion.nombreRegex;
                // si es a diferente estado, lo enlazamos con el nuevo estado 
                } else {
                    cadena += "\"];\n" + estado.nombreEstado + "->" + transicion.estadoDestino.nombreEstado + "[label=\"" + transicion.nombreRegex;
                }
                repetido = transicion.estadoDestino.nombreEstado;
            }
            repetido = "";
        }
        cadena += "\"]";
        return cadena;
    }
}

class Estado {
    int id;
    String nombreEstado;
    boolean esAceptacion;
    ArrayList<TransicionEstado> transiciones;
    
    public Estado(int id, String nombreEstado, boolean esAceptacion) {
        this.id = id;
        this.nombreEstado = nombreEstado;
        this.esAceptacion = esAceptacion;
        this.transiciones = new ArrayList<>();
    }
    
    public void add(Hoja hoja, ArrayList<Estado> estados) {
        for(Estado estado : estados) {
            if(hoja.estado.equals(estado.nombreEstado)) {
                this.transiciones.add(new TransicionEstado(hoja.nombre, estado));
            }
        }
    }
}

class TransicionEstado {
    String nombreRegex;
    String regex;
    Estado estadoDestino;

    public TransicionEstado(String nombreRegex, Estado estadoDestino) {
        this.nombreRegex = nombreRegex;
        this.estadoDestino = estadoDestino;
        this.regex = "";
    }
}

package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AFD {

    // aquí estarán todos los estados
    ArrayList<Estado> estados;
    public String nombreRegex;

    public AFD(String nombreRegex) {
        this.nombreRegex = nombreRegex;
        this.estados = new ArrayList<>();
    }

    public void tablaTransiciones(ArrayList<Transicion> tablaTransiciones) {
        // primero agregamos todos los estados al array
        int id = 0;
        for (Transicion transicion : tablaTransiciones) {
            estados.add(new Estado(id, transicion.estado, transicion.esAceptacion));
            id++;
        }
        // ahora enlazamos cada trancisicion con un estado
        for (Estado estado : this.estados) {
            for (Transicion transicion : tablaTransiciones) {
                for (Hoja hoja : transicion.transiciones) {
                    if (estado.nombreEstado.equals(transicion.estado)) {
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
        for (Estado estado : this.estados) {
            for (TransicionEstado transicion : estado.transiciones) {
                for (Conjunto conjunto : conjuntos) {
                    if (transicion.nombreRegex.contains(conjunto.nombreVariable)) {
                        transicion.rango = conjunto.rango;
                        if(conjunto.esRango)
                            transicion.esRango = true;
                        break;
                    } else if (transicion.nombreRegex.contains("\"")) {
                        transicion.rango = new String[1];
                        transicion.rango[0] = transicion.nombreRegex.replace("\\\"", "");
                        transicion.rango[0] = transicion.rango[0].replace("\\\'", "\'");
                        transicion.rango[0] = transicion.rango[0].replace("\\n", "\n");
                        break;
                    }
                }
            }
        }
    }

    public boolean evaluar(RegExpEvaluar valor) {
        String estadoActual = "S0";
        String caracterEspecial = "";
        // recorriendo el string
        for (char c : valor.cadena.toCharArray()) {
            if(c == '\\') {
                caracterEspecial += "\\"; 
                continue;
            } else if(caracterEspecial.equals("\\")) {
                caracterEspecial += c;
                if(caracterEspecial.equals("\\\'")) {
                    c = '\'';
                } else if(caracterEspecial.equals("\\n")) {
                    c = '\n';
                }
                caracterEspecial = "";
            }
            valor.esAceptado = false;
            // recorriendo los estados
            for (Estado estado : this.estados) {
                if (estado.nombreEstado.equals(estadoActual)) {
                    // recorriendo las transiciones
                    for (TransicionEstado transicion : estado.transiciones) {
                        // en este caso tiene que dar match con los posibles carateres
                        if (!transicion.esRango) {
                            for (String r : transicion.rango) {
                                char rc;
                                switch (r) {
                                    case "\n":
                                        rc = '\n';
                                        break;
                                    case "\'":
                                        rc = '\'';
                                        break;
                                    case "\"":
                                        rc = '\"';
                                        break;
                                    case "":
                                        rc = ' ';
                                        break;
                                    default:
                                        rc = r.charAt(0);
                                        break;
                                }
                                if (rc == c) {
                                    valor.esAceptado = true;
                                    estadoActual = transicion.estadoDestino.nombreEstado;
                                    break;
                                }
                            }
                            // en este caso tiene que da match con el rango que esta encerrado entre los dos caracteres    
                        } else {
                            int inferior = transicion.rango[0].charAt(0);
                            int superior = transicion.rango[1].charAt(0);
                            if ((int) c >= inferior && (int) c <= superior) {
                                valor.esAceptado = true;
                                estadoActual = transicion.estadoDestino.nombreEstado;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            if (!valor.esAceptado) {
                return false;
            }
        }
        if (valor.esAceptado) {
            for (Estado estado : this.estados) {
                if (estadoActual.equals(estado.nombreEstado)) {
                    return estado.esAceptacion;
                }
            }
        }
        return false;
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
        for (Estado estado : this.estados) {
            if (estado.esAceptacion) {
                cadena += estado.nombreEstado + "[shape=\"doublecircle\"];\n";
            } else {
                cadena += estado.nombreEstado + "[shape=\"circle\"];\n";
            }
        }
        return cadena;
    }

    public String enlazarGraphviz() {
        String cadena = "", repetido = "inicio";
        for (Estado estado : this.estados) {
            for (TransicionEstado transicion : estado.transiciones) {
                // si la transicion es hacia el mismo estado pero con otra hoja, solo los separamos por |
                if (repetido.equals(transicion.estadoDestino.nombreEstado)) {
                    cadena += "|" + transicion.nombreRegex;
                    // si es el inicio del automata lo enlazamos con el nodo inicio
                } else if (repetido.equals("inicio")) {
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
        for (Estado estado : estados) {
            if (hoja.estado.equals(estado.nombreEstado)) {
                this.transiciones.add(new TransicionEstado(hoja.nombre, estado));
            }
        }
    }
}

class TransicionEstado {

    String nombreRegex;
    String[] rango;
    Estado estadoDestino;
    boolean esRango;
    
    public TransicionEstado(String nombreRegex, Estado estadoDestino) {
        this.nombreRegex = nombreRegex;
        this.estadoDestino = estadoDestino;
        this.rango = null;
        this.esRango = false;
    }
}

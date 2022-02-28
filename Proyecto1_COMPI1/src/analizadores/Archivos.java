package analizadores;

import clases.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import clases.Error;

public class Archivos {

    // esta variable global me servirá para saber que archivo se modificará y se mostrará en la consola
    public static String rutaG = "";
    public ArrayList<Error> errores = new ArrayList<>();
    public ArrayList<Conjunto> conjuntos = new ArrayList<>();
    public ArrayList<ExpresionRegular> expresionesRegulares = new ArrayList<>();
    public ArrayList<RegExpEvaluar> evaluaciones = new ArrayList<>();
    
    public Archivos() {
    }

    public void nuevo(String ruta) throws IOException {
        // creando un archivo nuevo
        ruta += ".exp";
        rutaG = ruta;
        FileWriter fileWriter = new FileWriter(ruta);
        fileWriter.close();
    }
    
    public void guardarComo(String ruta, String contenido)throws IOException {
        // creando un archivo nuevo
        ruta += ".exp";
        rutaG = ruta;
        FileWriter fileWriter;
        fileWriter = new FileWriter(ruta);
        fileWriter.write(contenido);
        fileWriter.close();
    }
    
    public String analizadorLexico(String expr) throws IOException {
        // === Variables y objetos para recuperar la info necesaria
        String flag = "", variable = "", regex = "";
        Conjunto conjunto = new Conjunto();
        ExpresionRegular expresionRegular = new ExpresionRegular();
        RegExpEvaluar regExpEvaluar = new RegExpEvaluar();
        // ========================================================
        int linea = 1, columna = 0;
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "";
        while(true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                return resultado;
            }
            switch(token) {
                case SaltoLinea:
                    columna = 0;
                    linea++;
                    break;
                case LlaveAbre:
                    regex += lexer.lexeme;
                    columna += lexer.yylength();
                    break;
                case LlaveCierre:
                    regex += lexer.lexeme + "-";
                    columna += lexer.yylength();
                    break;
                case Conjunto:
                    // creando el objeto conjunto para guardar su informacion
                    flag = "CONJUNTO";
                    conjunto = new Conjunto();
                    
                    columna += lexer.yylength();    
                    break;
                case Separador:
                    columna += lexer.yylength();
                    break;
                case DosPuntos:
                    if (flag.equals("")) {
                        regExpEvaluar = new RegExpEvaluar();
                        regExpEvaluar.nombreVariable = variable;
                        flag = "EVALUACION";
                    }
                    columna += lexer.yylength();
                    break;
                case Asignador:
                    if (flag.equals("")) {
                        expresionRegular = new ExpresionRegular();
                        expresionRegular.nombreVariable = variable;
                        flag = "EXPRESION REGULAR";
                    }
                    columna += lexer.yylength();
                    break;
                case Rango:
                    // añadiendo el rango del conjunto
                    if (flag.equals("CONJUNTO") && conjunto != null) {
                        if(lexer.lexeme.contains("~")) {
                            conjunto.rango = lexer.lexeme.split("~");
                        } else if(lexer.lexeme.contains(",")) {
                            conjunto.rango = lexer.lexeme.split(",");
                        }
                    }
                    
                    columna += lexer.yylength();
                    break;
                case PuntoComa:
                    if (flag.equals("CONJUNTO")) {
                        conjuntos.add(conjunto);
                    } else if (flag.equals("EXPRESION REGULAR")) {
                        regex = regex.replace(expresionRegular.nombreVariable, "");
                        String aux[] = regex.split("-");
                        expresionRegular.expresionRegular = aux;
                        expresionesRegulares.add(expresionRegular);
                    } else if (flag.equals("EVALUACION")) {
                        evaluaciones.add(regExpEvaluar);
                    }
                    flag = "";
                    variable = "";
                    regex = "";
                    columna += lexer.yylength();
                    break;
                case Punto:
                    regex += lexer.lexeme + "-";
                    columna += lexer.yylength();
                    break;
                case Asterisco:
                    regex += lexer.lexeme + "-";
                    columna += lexer.yylength();
                    break;
                case Comilla:
                    columna += lexer.yylength();
                    break;
                case Barra:
                    regex += lexer.lexeme + "-";
                    columna += lexer.yylength();
                    break;
                case Negativo:
                    columna += lexer.yylength();
                    break;
                case Identificador:
                    regex += lexer.lexeme;
                    // añadiendo el nombre de la variable del conjunto
                    if (flag.equals("CONJUNTO") && conjunto != null) {
                        conjunto.nombreVariable = lexer.lexeme;
                    } else {
                        // sino guardaremos temporalmente el nombre de la variable
                        variable = lexer.lexeme;
                    }
                    columna += lexer.yylength();
                    break;
                case Numero:
                    columna += lexer.yylength();
                    break;
                case Mas:
                    regex += lexer.lexeme + "-";
                    columna += lexer.yylength();
                    break;
                case Interrogacion:
                    regex += lexer.lexeme + "-";
                    columna += lexer.yylength();
                    break;
                case Coma:
                    columna += lexer.yylength();
                    break;
                case ComillaSimple:
                    columna += lexer.yylength();
                    break;
                case Cadena:
                    lexer.lexeme = lexer.lexeme.replace("\\\"", "\"");
                    lexer.lexeme = lexer.lexeme.replace("\"", "\\\"");   
                    regex += lexer.lexeme + "-";
                    if (flag.equals("EVALUACION")) {
                        regExpEvaluar.cadena = lexer.lexeme;                                         
                    }
                    columna += lexer.yylength();
                    break;
                case ERROR:
                    columna += lexer.yylength();
                    String descripcion = "El caracter \"" + lexer.lexeme + "\" no se ha reconocido.";
                    this.errores.add(new Error("Lexico", descripcion, linea, columna));
                    resultado += "<Error>\t" + lexer.lexeme + "    linea: " + linea + "    columna: " + columna + "\n";
                    break;
                default:
                    columna += lexer.yylength();
                    resultado += "<" + lexer.lexeme + ">\n";
            }
        }
    }
    
    public String analizadorSintactico(String expr) {
        Sintax s = new Sintax(new analizadores.LexerCup(new StringReader(expr)));  
        try {            
            s.parse();         
            return "Análisis realizado correctamente.";
        } catch (Exception ex) {   
            Symbol sym = s.getS();
            String descripcion = "El componente \"" + sym.value + "\" no se esperaba.";
            this.errores.add(new Error("Sintáctico", descripcion, sym.right, sym.left));
            return "Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + (sym.value) + "\"\n";
        }
    }
}

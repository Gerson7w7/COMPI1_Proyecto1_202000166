package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Html {
    
    public FileWriter fichero = null;
    public static PrintWriter pw = null;
    
    public Html() {
    }

    public void reportErrores(int numero, ArrayList<Error> errores) {
        try {
            fichero = new FileWriter("REPORTES/ERRORES_202000166/errores_" + numero + ".html");
            pw = new PrintWriter(fichero);

            pw.println("<!DOCTYPE html><!--Declarar el tipo de cumento -->\n"
                    + "<html>\n"
                    + "\n"
                    + "<!--Encabezado-->\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\"><!--codififcaion de caracteres ñ y á-->\n"
                    + "\n"
                    + "\n"
                    + "<meta name=\"name\" content=\"Reporte\"><!--nombre de la pagina-->\n"
                    + "<meta name=\"description\" content=\"name\"><!--autor de la pagina-->\n"
                    + "<meta name=\"keywods\" content=\"uno,dos,tres\"><!--Palabras claavez para, separadas por comas-->\n"
                    + "<meta name=\"robots\" content=\"Index, Follow\"><!--Mejora la busqueda-->\n"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><!--visibilidaad en diferentes pantallas -->\n"
                    + "\n"
                    + "\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/><!--css /estilo/tipo/ruta relativa -->\n"
                    + "\n"
                    + "<title>Errores</title><!--Titulo visible de la pagina-->\n"
                    + "</head>\n"
                    + "\n");
            pw.println("<body>\n"
                    + "\n"
                    + "<center><!--centra todos lo que este dentro--> \n"
                    + "<h6 class=titulos><b> ERRORES de la ejecución " + numero + "</b></h6>");

            pw.println(" <br>  <br>  <br> \n"
                    + "\n"
                    + "<!----tabla 2-->\n"
                    + "<table class=\"steelBlueCols\">\n"
                    + "<thead>\n"
                    + "   <tr><th>No</th> <th>Tipo de error</th> <th>Descripcion</th><th>Linea</th><th>Columna</th></tr>\n"
                    + "</thead>\n"
                    + "<tbody>\n");
            int i = 1;
            for (Error error : errores) {
                    pw.println(" <tr>");
                    pw.println("<td>" + String.valueOf(i) + "</td>");
                    pw.println("<td>" + String.valueOf(error.tipo) + "</td>");
                    pw.println("<td>" + String.valueOf(error.descripcion) + "</td>");
                    pw.println("<td>" + String.valueOf(error.linea) + "</td>");
                    pw.println("<td>" + String.valueOf(error.columna) + "</td>");
                    pw.println("</tr>");
                    i++;
            }
            pw.println("</tr> \n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + " <!----termina tabla 2-->");

            pw.println("</center>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");

            fichero.close();
        } catch (IOException e) {
        }
    }
    
    public void reportSiguientes(String nombreArchivo, Siguiente []tablaSiguientes) {
        try {
            fichero = new FileWriter("REPORTES/SIGUIENTES_202000166/" + nombreArchivo + ".html");
            pw = new PrintWriter(fichero);

            pw.println("<!DOCTYPE html><!--Declarar el tipo de cumento -->\n"
                    + "<html>\n"
                    + "\n"
                    + "<!--Encabezado-->\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\"><!--codififcaion de caracteres ñ y á-->\n"
                    + "\n"
                    + "\n"
                    + "<meta name=\"name\" content=\"Reporte\"><!--nombre de la pagina-->\n"
                    + "<meta name=\"description\" content=\"name\"><!--autor de la pagina-->\n"
                    + "<meta name=\"keywods\" content=\"uno,dos,tres\"><!--Palabras claavez para, separadas por comas-->\n"
                    + "<meta name=\"robots\" content=\"Index, Follow\"><!--Mejora la busqueda-->\n"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><!--visibilidaad en diferentes pantallas -->\n"
                    + "\n"
                    + "\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/><!--css /estilo/tipo/ruta relativa -->\n"
                    + "\n"
                    + "<title>Siguientes</title><!--Titulo visible de la pagina-->\n"
                    + "</head>\n"
                    + "\n");
            pw.println("<body>\n"
                    + "\n"
                    + "<center><!--centra todos lo que este dentro--> \n"
                    + "<h6 class=titulos><b> SIGUIENTES de " + nombreArchivo + "</b></h6>");

            pw.println(" <br>  <br>  <br> \n"
                    + "\n"
                    + "<!----tabla 2-->\n"
                    + "<table class=\"steelBlueCols\">\n"
                    + "<thead>\n"
                    + "   <tr><th colspan=\"2\">Hojas</th> <th>Siguientes</th></tr>\n"
                    + "</thead>\n"
                    + "<tbody>\n");
            int i = 1;
            for (Siguiente siguiente : tablaSiguientes) {
                    pw.println(" <tr>");
                    pw.println("<td>" + String.valueOf(siguiente.hoja.replace("\\\"", "\"")) + "</td>");
                    pw.println("<td>" + String.valueOf(siguiente.identificador) + "</td>");
                    pw.println("<td>");
                    if(siguiente.siguientes != null) {
                        for (int siguienteInterno : siguiente.siguientes) {
                            pw.println(String.valueOf(siguienteInterno) + ",");
                        }
                    } else {
                        pw.println("-");
                    }                    
                    pw.println("</td>");
                    pw.println("</tr>");
                    i++;
            }
            pw.println("</tr> \n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + " <!----termina tabla 2-->");

            pw.println("</center>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");

            fichero.close();
        } catch (IOException e) {
        }
    }
    
    public void reportTransicion(String nombreArchivo, ArrayList<Transicion> tablaTransiciones) {
        try {
            fichero = new FileWriter("REPORTES/TRANSICIONES_202000166/" + nombreArchivo + ".html");
            pw = new PrintWriter(fichero);

            pw.println("<!DOCTYPE html><!--Declarar el tipo de cumento -->\n"
                    + "<html>\n"
                    + "\n"
                    + "<!--Encabezado-->\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\"><!--codififcaion de caracteres ñ y á-->\n"
                    + "\n"
                    + "\n"
                    + "<meta name=\"name\" content=\"Reporte\"><!--nombre de la pagina-->\n"
                    + "<meta name=\"description\" content=\"name\"><!--autor de la pagina-->\n"
                    + "<meta name=\"keywods\" content=\"uno,dos,tres\"><!--Palabras claavez para, separadas por comas-->\n"
                    + "<meta name=\"robots\" content=\"Index, Follow\"><!--Mejora la busqueda-->\n"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><!--visibilidaad en diferentes pantallas -->\n"
                    + "\n"
                    + "\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/><!--css /estilo/tipo/ruta relativa -->\n"
                    + "\n"
                    + "<title>Transiciones</title><!--Titulo visible de la pagina-->\n"
                    + "</head>\n"
                    + "\n");
            pw.println("<body>\n"
                    + "\n"
                    + "<center><!--centra todos lo que este dentro--> \n"
                    + "<h6 class=titulos><b> TRANSICIONES de " + nombreArchivo +  "</b></h6>");

            pw.println(" <br>  <br>  <br> \n"
                    + "\n"
                    + "<!----tabla 2-->\n"
                    + "<table class=\"steelBlueCols\">\n"
                    + "<thead><tr>\n"
                    + "   <th>Aceptación</th><th>Estado</th>\n");
            for(Hoja hoja : tablaTransiciones.get(0).transiciones) {
                pw.println("<th>" + hoja.nombre.replace("\\\"", "\"") + "</th>");
            }
            pw.println("</tr></thead>\n"
                    + "<tbody>\n");
            for (Transicion transicion : tablaTransiciones) {
                    pw.println(" <tr>");
                    if(transicion.esAceptacion) {
                        pw.println("<td>Si</td>");
                    } else {
                        pw.println("<td></td>");
                    }
                    pw.println("<td>" + String.valueOf(transicion.estado) + "</td>");
                    for(Hoja hoja : transicion.transiciones) {
                        if(!hoja.estado.equals("")) {
                            pw.println("<td>" + String.valueOf(hoja.estado) + "</td>");
                        }else {
                            pw.println("<td>-</td>");
                        }                       
                    }
                    pw.println("</tr>");                                      
            }
            pw.println("</tr> \n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + " <!----termina tabla 2-->");

            pw.println("</center>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");

            fichero.close();
        } catch (IOException e) {
        }
    }
}

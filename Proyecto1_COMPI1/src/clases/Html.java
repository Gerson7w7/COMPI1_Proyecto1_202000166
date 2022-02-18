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
                    + "<h6 class=titulos><b> ERRORES </b></h6>");

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
            System.out.println("El reporte se ha generado correctamente :D \n");
        } catch (IOException e) {
        }
    }
}

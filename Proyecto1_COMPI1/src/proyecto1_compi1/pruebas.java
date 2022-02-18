/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto1_compi1;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class pruebas {
    public static void main(String[] args) {
        String hola = "\"holaaa\"";
        hola = hola.replace("\"", "\\\"");
        System.out.println(hola);
    }
}

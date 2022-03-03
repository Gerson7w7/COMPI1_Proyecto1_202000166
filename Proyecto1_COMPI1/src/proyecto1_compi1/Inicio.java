/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_compi1;

import analizadores.Archivos;
import clases.AFD;
import clases.AFN;
import clases.AST;
import clases.ExpresionRegular;
import clases.Html;
import clases.RegExpEvaluar;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Inicio extends javax.swing.JFrame {

    Archivos archivo = new Archivos();
    ArrayList<AFD> afds = new ArrayList();
    int siguiente = 0;

    /**
     * Creates new form Inicio
     */
    int contador = 1;

    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        imagenLabel = new javax.swing.JLabel();
        imagenes = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 102));

        jButton1.setText("Nuevo");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Abrir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Guardar como");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("Salir");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Archivo fuente");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel2.setText("Salida:");

        jButton8.setText("Analizar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        imagenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AFD", "AFN", "ARBOLES" }));
        imagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagenesActionPerformed(evt);
            }
        });

        jButton9.setText("Siguiente");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel3.setText("Reportes gráficos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(312, 804, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imagenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton8)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(imagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jScrollPane2))))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jButton8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imagenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // BOTÓN SALIR
        System.exit(0);
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // Crear un nuevo archivo
        Rutas ruta = new Rutas("");
        ruta.setVisible(true);
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // Abrir un archivo ya creado
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.exp", "exp");
        fc.setFileFilter(filtro);
        int seleccion = fc.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            Archivos.rutaG = fichero.getAbsolutePath();

            try (FileReader fr = new FileReader(fichero)) {
                String cadena = "";
                int valor = fr.read();
                while (valor != -1) {
                    cadena = cadena + (char) valor;
                    valor = fr.read();
                }
                this.jTextArea1.setText(cadena);
            } catch (IOException e) {

            }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // guardar archivo actual
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(Archivos.rutaG);
            fileWriter.write(jTextArea1.getText());
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Se ha guardado con éxito");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error. No se ha abierto ningún archivo.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // guardar como
        Rutas ruta = new Rutas(jTextArea1.getText());
        ruta.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // Botón analizar

        String resultado = "";
        try {
            // analizador lexico
            resultado = archivo.analizadorLexico((String) jTextArea1.getText());
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        // analizador sintáctico
        resultado += archivo.analizadorSintactico((String) jTextArea1.getText());
        // seteando el text area con la info necesaria
        jTextArea2.setText(resultado);
        // lógica de las evaluaciones de las expresiones regulares
        if (resultado.contains("Análisis realizado correctamente.")) {
            AST arbol;
            resultado += "\nAUTOMATAS\n";
            for (ExpresionRegular exp : archivo.expresionesRegulares) {
                // primer reporte (arbol sintactico)
                arbol = new AST(exp.nombreVariable);
                arbol.regex(exp.expresionRegular);
                arbol.anulables(arbol.raiz);
                arbol.id = 1;
                arbol.primerosUltimos(arbol.raiz);
                arbol.graphviz();
                // segundo reporte (tabla de siguientes)
                arbol.siguientes();
                // tercer reporte (tabla de transiciones)
                arbol.transiciones();
                // automata AFD 
                AFD afd = new AFD(exp.nombreVariable);
                afd.tablaTransiciones(arbol.tablaTransiciones);
                afd.introRegex(archivo.conjuntos);
                afd.graphviz();
                this.afds.add(afd);
                // automata AFN (Thompson)
                AFN afn = new AFN(exp.nombreVariable);
                afn.recorridoAST(arbol.raiz);
                afn.addInicio();
                afn.graphviz();
                resultado += "Autómata finito determinista de " + exp.nombreVariable + " creado con éxito.\n";
            }
            // por último validamos las cadenas mediante los autómatas creados
            boolean esAceptado;
            for (RegExpEvaluar evaluacion : archivo.evaluaciones) {
                evaluacion.cadena = evaluacion.cadena.replace("\\\"", "");
                for (AFD automata : this.afds) {
                    esAceptado = automata.evaluar(evaluacion);
                    if (esAceptado) {
                        evaluacion.matchCon = automata.nombreRegex;
                        evaluacion.esAceptado = true;
                        break;
                    } else {
                        evaluacion.esAceptado = false;
                    }
                }
            }
            // craeando el reporte de las salidas mediante json
            resultado += "\nRESULTADOS\n";
            String json = "";
            int i = 1;
            for (RegExpEvaluar evaluacion : archivo.evaluaciones) {
                json += "   {\n";
                json += "       \"Valor\":\"" + evaluacion.cadena + "\",\n";
                if (evaluacion.esAceptado) {
                    json += "       \"ExpresionRegular\":\"" + evaluacion.matchCon + "\",\n";
                    json += "       \"Resultado\":\"Cadena válida\"\n";
                    resultado += "La expresion: \"" + evaluacion.cadena + "\", es válida con la expresion regular: " + evaluacion.matchCon + "\n\n";
                } else {
                    json += "       \"ExpresionRegular\":\"No aplica\",\n";
                    json += "       \"Resultado\":\"Cadena inválida\"\n";
                    resultado += "La expresion: \"" + evaluacion.cadena + "\", NO es válida con ninguna expresion regular\n\n";
                }
                if (i != archivo.evaluaciones.size()) {
                    json += "   },\n";
                } else {
                    json += "   }\n";
                }
                i++;
            }
            archivo.crearJson(contador, json);
            // seteando el text area con la info necesaria
            jTextArea2.setText(resultado);
        } else {
            // creando reportes
            Html repErrores = new Html();
            repErrores.reportErrores(contador, archivo.errores);
        }
        contador++;
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.siguiente++;
        System.out.println(siguiente);
        if (siguiente == archivo.expresionesRegulares.size()) {
            this.siguiente = 0;
        }
        String ruta = "REPORTES/";
        switch (imagenes.getSelectedIndex()) {
            case 0:
                // AFD
                ruta += "AFD_202000166/" + archivo.expresionesRegulares.get(this.siguiente).nombreVariable + ".png";
                mostrarImagen(ruta);
                break;
            case 1:
                // AFN
                ruta += "AFND_202000166/" + archivo.expresionesRegulares.get(this.siguiente).nombreVariable + ".png";
                mostrarImagen(ruta);
                break;
            case 2:
                // ARBOLES
                ruta += "ARBOLES_202000166/" + archivo.expresionesRegulares.get(this.siguiente).nombreVariable + ".png";
                mostrarImagen(ruta);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void imagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagenesActionPerformed
        // TODO add your handling code here:       
        String ruta = "REPORTES/";
        try {
            switch (imagenes.getSelectedIndex()) {
                case 0:
                    // AFD
                    ruta += "AFD_202000166/" + archivo.expresionesRegulares.get(0).nombreVariable + ".png";
                    mostrarImagen(ruta);
                    break;
                case 1:
                    // AFN
                    ruta += "AFND_202000166/" + archivo.expresionesRegulares.get(0).nombreVariable + ".png";
                    mostrarImagen(ruta);
                    break;
                case 2:
                    // ARBOLES
                    ruta += "ARBOLES_202000166/" + archivo.expresionesRegulares.get(0).nombreVariable + ".png";
                    mostrarImagen(ruta);
                    break;
                default:
                    break;
            }
        } catch(Exception e) {
            // seteando el text area con la info necesaria
            jTextArea2.setText("Error al mostrar la imagen. Debería de analizar algún archivo primero.");
        }
    }//GEN-LAST:event_imagenesActionPerformed

    void mostrarImagen(String ruta) {
        ImageIcon imagen = new ImageIcon(ruta);
        ImageIcon icono = new ImageIcon(
                imagen.getImage().getScaledInstance(this.imagenLabel.getWidth(),
                        this.imagenLabel.getHeight(),
                        Image.SCALE_SMOOTH)
        );
        this.imagenLabel.setIcon(icono);
        this.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JComboBox<String> imagenes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}

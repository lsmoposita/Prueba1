/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinaal;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author User
 */
public class estado extends javax.swing.JFrame {

    private Document usuario;
    private String nombreUsuario; // Agrega esta variable
    /**
     * Creates new form estado
     */
    
    public estado(Document usuario) {
        initComponents();
        this.usuario = usuario;
        // Obtener el valor del campo "usuario" del documento
        nombreUsuario = usuario.getString("nombre");
    
    }

    private void mostrarRegistrosLuces() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Fecha/Hora");
        tableModel.addColumn("Usuario");
        tableModel.addColumn("Acción");

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("domotica");
            MongoCollection<Document> lucesCollection = database.getCollection("luces");

            FindIterable<Document> registros = lucesCollection.find();
            for (Document registro : registros) {
                String fechaHoraStr = registro.getString("fechaHora"); // Obtiene la fecha como cadena
                LocalDateTime fechahora = parseLocalDateTime(fechaHoraStr); // Parsea la cadena a LocalDateTime
                String usuario = registro.getString("usuario");
                String accion = registro.getString("accion");

                tableModel.addRow(new Object[]{fechahora, usuario, accion});
            }

            jTableEstado.setModel(tableModel);

            mongoClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     // Función de utilidad para parsear una cadena a LocalDateTime
    private LocalDateTime parseLocalDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstado = new javax.swing.JTable();
        jButtonMostrar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Edwardian Script ITC", 1, 36)); // NOI18N
        jLabel1.setText("Estado");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 100, 40));

        jTableEstado.setBackground(new java.awt.Color(102, 204, 255));
        jTableEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableEstado);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 640, -1));

        jButtonMostrar.setFont(new java.awt.Font("Edwardian Script ITC", 1, 36)); // NOI18N
        jButtonMostrar.setText("Mostrar");
        jButtonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 150, 50));

        jButton1.setFont(new java.awt.Font("Edwardian Script ITC", 1, 36)); // NOI18N
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 150, 50));

        jLabelFondo.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Desktop\\Proyecto Final POO\\fondoDomotica.jpeg")); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarActionPerformed
        mostrarRegistrosLuces();
    }//GEN-LAST:event_jButtonMostrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // Cerrar la ventana actual
    dispose();

    // Crear una instancia de la ventana anterior (Menu)
    Domotica menu = new Domotica(usuario);
    
    // Hacer visible el JFrame
    menu.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonMostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstado;
    // End of variables declaration//GEN-END:variables
}

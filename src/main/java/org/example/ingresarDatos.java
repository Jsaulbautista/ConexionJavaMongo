package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ingresarDatos {
    private JTextField nombreField;
    private JTextField hobbyField;
    private JButton ingresarDatos;
    public JPanel ingresarDatosPanel;
    private JTextArea textArea1;
    private JButton buscarButton;
    private JLabel descripcionlabel;
    private JTextField descripcionField;

    private int contador = 1;



    public ingresarDatos() {

        ingresarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb+srv://saul:12345654321@saul.ey7q2vo.mongodb.net/?retryWrites=true&w=majority&appName=Saul");


                MongoDatabase database = mongoClient.getDatabase("ConexionJava");
                MongoCollection<Document> collection = database.getCollection("usuarios");

                Document document = new Document("Nombre", nombreField.getText())
                        .append("Hobby", hobbyField.getText())
                        .append("Descripción", descripcionField.getText());

                collection.insertOne(document);
                System.out.println("Documento insertado");

//                // Mostrar los datos en el JTextArea en formato JSON
//                String currentText = textArea1.getText();
//                textArea1.setText(currentText + "\n" + document.toJson());

                // Mostrar los datos en el JTextArea en formato JSON
                StringBuilder builder = new StringBuilder(textArea1.getText());
                builder.append("\n");
                builder.append(contador).append(". ").append(document.toJson()).append("\n");

                textArea1.setText(builder.toString());
                contador++;

                JFrame salIns = new JFrame("Mensaje");
                JOptionPane.showMessageDialog(salIns, "Datos ingresados correctamente");
            }
        });
        // Cargar todos los documentos existentes al iniciar la aplicación
        cargarDocumentos();

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar al usuario el nombre a buscar mediante un cuadro de diálogo
                String nombreABuscar = JOptionPane.showInputDialog("Ingrese el nombre a buscar:");

                // Verificar si el usuario ingresó un nombre
                if (nombreABuscar != null && !nombreABuscar.isEmpty()) {
                    // Realizar la búsqueda por nombre
                    buscarPorNombre(nombreABuscar);
                } else {
                    // Mostrar mensaje si no se ingresó un nombre válido
                    mostrarMensaje("Nombre no válido. No se realizó la búsqueda.");
                }
            }
        });
    }
    private void cargarDocumentos() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://saul:12345654321@saul.ey7q2vo.mongodb.net/?retryWrites=true&w=majority&appName=Saul");

        MongoDatabase database = mongoClient.getDatabase("ConexionJava");
        MongoCollection<Document> collection = database.getCollection("usuarios");

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                StringBuilder builder = new StringBuilder(textArea1.getText());
                builder.append("\n");
                builder.append(contador).append(". ").append(document.toJson()).append("\n");
                textArea1.setText(builder.toString());
                contador++;
            }
        } finally {
            cursor.close();
        }
    }
    private void buscarPorNombre(String nombre) {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://saul:12345654321@saul.ey7q2vo.mongodb.net/?retryWrites=true&w=majority&appName=Saul");

        MongoDatabase database = mongoClient.getDatabase("ConexionJava");
        MongoCollection<Document> collection = database.getCollection("usuarios");

        // Realizar la búsqueda por nombre
        Document document = collection.find(Filters.eq("nombre", nombre)).first();

        if (document != null) {
            // Mostrar la información en un JOptionPane
            mostrarMensaje("Información encontrada:\n" + document.toJson());

            // Establecer la información encontrada en los campos respectivos
            nombreField.setText(document.getString("nombre"));
            hobbyField.setText(document.getString("apellido"));
        } else {
            mostrarMensaje("No se encontró información para el nombre: " + nombre);
        }
    }

    private void mostrarMensaje(String mensaje) {
        JFrame salIns = new JFrame("Mensaje");
        JOptionPane.showMessageDialog(salIns, mensaje);
    }


}

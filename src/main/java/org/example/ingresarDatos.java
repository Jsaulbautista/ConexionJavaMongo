package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ingresarDatos {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JButton ingresarDatos;
    public JPanel ingresarDatosPanel;

    public ingresarDatos() {
        ingresarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb+srv://saul:12345654321@saul.ey7q2vo.mongodb.net/?retryWrites=true&w=majority&appName=Saul");


                MongoDatabase database = mongoClient.getDatabase("ConexionJava");
                MongoCollection<Document> collection = database.getCollection("usuarios");

                Document document = new Document("nombre", nombreField.getText())
                        .append("apellido", apellidoField.getText());

                collection.insertOne(document);
                System.out.println("Documento insertado");

                JFrame salIns = new JFrame("Mensaje");
                JOptionPane.showMessageDialog(salIns, "Datos ingresados correctamente");
            }
        });
    }
}

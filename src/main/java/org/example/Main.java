package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ingresar calificacion");
        frame.setContentPane(new ingresarDatos().ingresarDatosPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,300);
        frame.setVisible(true);

    }
}
package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ingresar Datos");
        frame.setContentPane(new ingresarDatos().ingresarDatosPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,500);
        frame.setVisible(true);

    }
}
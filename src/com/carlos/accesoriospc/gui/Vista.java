package com.carlos.accesoriospc.gui;

import com.carlos.accesoriospc.base.Perifericos;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    private JRadioButton tecladoRadioButton;
    private JRadioButton ratonRadioButton;
    private JTextField marcaTxt;
    private JTextField modeloTxt;
    private JButton añadirButton;
    private JButton exportarButton;
    private JButton importarButton;
    private JTextField precioTxt;
    private JTextArea textArea1;
    private JList list1;

    public JFrame frame;
    public DefaultListModel<Perifericos> dlmperifericos;

    public Vista(){
        frame = new JFrame("Perifericos MVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //centrar
        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        dlmperifericos = new DefaultListModel<Perifericos>();
        list1.setModel(dlmperifericos);
    }
}

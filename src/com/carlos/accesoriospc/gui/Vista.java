package com.carlos.accesoriospc.gui;

import com.carlos.accesoriospc.base.Perifericos;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    public JRadioButton tecladoRadioButton;
    public JRadioButton ratonRadioButton;
    public JTextField fabricanteTxt;
    public JButton añadirButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JTextField precioTxt;
    public JList list1;
    public JComboBox inalambrico;
    public DatePicker fechaCompra;
    public JTextField opinionTxt;
    public JTextField pesoNumeroExistenciasTxt;
    public JTextField idPerifericoTxt;
    public JLabel pesoNumeroExistenciasLbl;

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
        inalambrico.addItem("Si");
        inalambrico.addItem("No");
        inalambrico.setSelectedIndex(-1); //Con esto el JCombox aparece sin ninguna opcion marcada al principio
    }
}

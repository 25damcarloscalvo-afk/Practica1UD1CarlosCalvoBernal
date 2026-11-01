package com.carlos.accesoriospc.gui;

import com.carlos.accesoriospc.base.Auriculares;
import com.carlos.accesoriospc.base.Perifericos;
import com.carlos.accesoriospc.base.Raton;
import com.carlos.accesoriospc.base.Teclado;
import com.carlos.accesoriospc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener {
    private Vista vista;
    private Modelo modelo;
    private File ultimaRutaExportada;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarDatosDeConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuracion" + e.getMessage());
        }

        addActionListener(this);
        addWindowListener(this);
        addLisSelectionListener(this);
    }

    private boolean hayCamposVacios() {
        if (vista.fabricanteTxt.getText().isEmpty() || vista.precioTxt.getText().isEmpty() ||
                vista.fechaCompra.getText().isEmpty() || /*vista.opinionTxt.getText().isEmpty() ||*/
                vista.tipoConexionPesoNumeroExistenciasTxt.getText().isEmpty() || vista.idPerifericoTxt.getText().isEmpty() ||
                vista.inalambrico.getSelectedIndex() == -1) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.idPerifericoTxt.setText(null);
        vista.fabricanteTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.fechaCompra.setText(null);
        vista.opinionTxt.setText(null);
        vista.tipoConexionPesoNumeroExistenciasTxt.setText(null);
        vista.inalambrico.setSelectedIndex(-1);
        vista.idPerifericoTxt.requestFocus();
    }

    public void refrescar() {
        vista.dlmperifericos.clear();
        for (Perifericos perifericos : modelo.obtenerPerifericos()) {
            vista.dlmperifericos.addElement(perifericos);
        }
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        if (ultimaRutaExportada != null) {
            configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        }
        configuracion.store(new PrintWriter("perifericos.conf"), "Datos configuracion perifericos");
    }

    private void cargarDatosDeConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("perifericos.conf"));
        String ruta = configuracion.getProperty("ultimaRutaExportada");
        if (ruta != null) {
            ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
        }
    }

    private void actualizarDatosConfiguracion (File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void addActionListener(ActionListener listener) {
        vista.añadirButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.ratonRadioButton.addActionListener(listener);
        vista.tecladoRadioButton.addActionListener(listener);
        vista.auricularesRadioButton.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addLisSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionComand = e.getActionCommand();
        switch (actionComand) {
            case "Añadir" :
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no puede estar vacios: \n" +
                            "id \n fabricante \n inalambrico\n precio \n Fecha compra \n opinion" +
                            vista.tipoConexionPesoNumeroExistenciasLbl.getText());
                    break;
                }

                if (modelo.existeIdPeriferico(Integer.parseInt(vista.idPerifericoTxt.getText()))) {
                    Util.mensajeError("Ya existe un periferico con este id \n" + vista.idPerifericoTxt.getText());
                    break;
                }

                if (vista.ratonRadioButton.isSelected()) {
                    modelo.altaRaton(Integer.parseInt(vista.idPerifericoTxt.getText()),
                            vista.inalambrico.getSelectedIndex() == 0,
                            vista.fabricanteTxt.getText(),
                            Integer.parseInt(vista.tipoConexionPesoNumeroExistenciasTxt.getText()),
                            vista.fechaCompra.getDate(),
                            Double.parseDouble(vista.precioTxt.getText()),
                            vista.opinionTxt.getText());
                } else if (vista.tecladoRadioButton.isSelected()){
                    modelo.altaTeclado(Integer.parseInt(vista.idPerifericoTxt.getText()),
                            vista.inalambrico.getSelectedIndex() == 0,
                            vista.fabricanteTxt.getText(),
                            vista.fechaCompra.getDate(),
                            Double.parseDouble(vista.precioTxt.getText()),
                            Integer.parseInt(vista.tipoConexionPesoNumeroExistenciasTxt.getText()),
                            vista.opinionTxt.getText());
                }else {
                    modelo.altaAuriculares(Integer.parseInt(vista.idPerifericoTxt.getText()),
                            vista.inalambrico.getSelectedIndex() == 0,
                            vista.fabricanteTxt.getText(),
                            vista.fechaCompra.getDate(),
                            Double.parseDouble(vista.precioTxt.getText()),
                            vista.opinionTxt.getText(),
                            vista.tipoConexionPesoNumeroExistenciasTxt.getText());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Exportar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos xml", "xml");
                int opcion = selectorFichero.showSaveDialog(null);

                if (opcion == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorFichero.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Importar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos xml", "xml");
                int opcion2 = selectorFichero2.showOpenDialog(null);

                if (opcion2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importar(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }
                refrescar();
                break;
            case "Raton":
                vista.tipoConexionPesoNumeroExistenciasLbl.setText("Nº existencias");
                break;
            case "Teclado":
                vista.tipoConexionPesoNumeroExistenciasLbl.setText("Peso");
                break;
            case "Auriculares":
                vista.tipoConexionPesoNumeroExistenciasLbl.setText("Tipo de conexión");
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Perifericos perifericoSeleccionado = (Perifericos) vista.list1.getSelectedValue();
            vista.idPerifericoTxt.setText(String.valueOf(perifericoSeleccionado.getIdPeriferico()));
            if (perifericoSeleccionado.isInalambrico()) {
                vista.inalambrico.setSelectedIndex(0);
            }else {
                vista.inalambrico.setSelectedIndex(1);
            }
            vista.fabricanteTxt.setText(perifericoSeleccionado.getFabricante());
            vista.precioTxt.setText(String.valueOf(perifericoSeleccionado.getPrecio()));
            vista.fechaCompra.setDate(perifericoSeleccionado.getFechaCompra());
            vista.opinionTxt.setText(perifericoSeleccionado.getOpinion());
            if (perifericoSeleccionado instanceof Raton) {
                vista.tipoConexionPesoNumeroExistenciasTxt.setText(String.valueOf(((Raton) perifericoSeleccionado).getNumeroExistencias()));
                vista.ratonRadioButton.doClick();
            }else if (perifericoSeleccionado instanceof  Teclado){
                vista.tipoConexionPesoNumeroExistenciasTxt.setText(String.valueOf(((Teclado) perifericoSeleccionado).getpeso()));
                vista.tecladoRadioButton.doClick();
            }else {
                vista.tipoConexionPesoNumeroExistenciasTxt.setText(((Auriculares) perifericoSeleccionado).getTipoConexion());
                vista.auricularesRadioButton.doClick();
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int resp = Util.mensajeConfirmacion("¿Desea salir de la aplicación?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

    //No se utilizan
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}

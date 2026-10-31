package com.carlos.accesoriospc.gui;

import com.carlos.accesoriospc.base.Perifericos;
import com.carlos.accesoriospc.base.Raton;
import com.carlos.accesoriospc.base.Teclado;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {
    public ArrayList<Perifericos> listaPerifericos;

    public Modelo() {
        listaPerifericos = new ArrayList<Perifericos>();
    }

    public ArrayList<Perifericos> obtenerPerifericos() {
        return listaPerifericos;
    }

    public void altaRaton(int idPeriferico, boolean inalambrico, String fabricante,
                          int numeroExistencias, LocalDate fechaCompra, double precio) {
        Raton nuevoRaton = new Raton(idPeriferico, inalambrico, fabricante, numeroExistencias, fechaCompra, precio);
        listaPerifericos.add(nuevoRaton);
    }
    public void altaTeclado(int idPeriferico, boolean inalambrico,
                            String fabricante, LocalDate fechaCompra, double precio, int peso) {
        Teclado nueTeclado = new Teclado(idPeriferico, inalambrico, fabricante, fechaCompra, precio, peso);
        listaPerifericos.add(nueTeclado);
    }

    public boolean existeIdPeriferico(int idPeriferico) {
        for (Perifericos periferico: listaPerifericos) {
            if (periferico.getIdPeriferico() == idPeriferico){
                return true;
            }
        }
        return false;
    }

    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz =  documento.createElement("perifericos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoPeriferico = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Perifericos periferico : listaPerifericos) {
            if (periferico instanceof Raton) {
                nodoPeriferico = documento.createElement("Raton");
            }else {
                nodoPeriferico = documento.createElement("Teclado");
            }

            raiz.appendChild(nodoPeriferico);

            nodoDatos = documento.createElement("idPeriferico");
            nodoPeriferico.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(periferico.getIdPeriferico()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("inalambrico");
            nodoPeriferico.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(periferico.isInalambrico()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fabricante");
            nodoPeriferico.appendChild(nodoDatos);

            texto = documento.createTextNode(periferico.getFabricante());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha compra");
            nodoPeriferico.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(periferico.getFechaCompra()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoPeriferico.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(periferico.getPrecio()));
            nodoDatos.appendChild(texto);

            if (periferico instanceof Raton) {
                nodoDatos = documento.createElement("numero existencias");
                nodoPeriferico.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(periferico.getPrecio()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos = documento.createElement("peso");
                nodoPeriferico.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(((Teclado) periferico).getpeso()));
                nodoDatos.appendChild(texto);
            }
        }

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    public void importar(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaPerifericos = new ArrayList<Perifericos>();
        Raton nuevoRaton = null;
        Teclado nuevoTeclado = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoPeriferico = (Element)listaElementos.item(i);

            if (nodoPeriferico.getTagName().equals("Raton")) {
                nuevoRaton = new Raton();
                nuevoRaton.setIdPeriferico(Integer.parseInt(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoRaton.setInalambrico(Boolean.parseBoolean(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoRaton.setFabricante(nodoPeriferico.getChildNodes().item(0).getTextContent());
                nuevoRaton.setFechaCompra(LocalDate.parse(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoRaton.setPrecio(Double.parseDouble(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoRaton.setNumeroExistencias(Integer.parseInt(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                listaPerifericos.add(nuevoRaton);
            }

            if (nodoPeriferico.getTagName().equals("Teclado")){
                nuevoTeclado.setIdPeriferico(Integer.parseInt(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoTeclado.setInalambrico(Boolean.parseBoolean(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoTeclado.setFabricante(nodoPeriferico.getChildNodes().item(0).getTextContent());
                nuevoTeclado.setFechaCompra(LocalDate.parse(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoTeclado.setPrecio(Double.parseDouble(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                nuevoTeclado.setpeso(Integer.parseInt(nodoPeriferico.getChildNodes().item(0).getTextContent()));
                listaPerifericos.add(nuevoTeclado);
            }
        }
    }
}

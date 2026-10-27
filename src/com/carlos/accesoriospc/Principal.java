package com.carlos.accesoriospc;

import com.carlos.accesoriospc.gui.Controlador;
import com.carlos.accesoriospc.gui.Modelo;
import com.carlos.accesoriospc.gui.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}

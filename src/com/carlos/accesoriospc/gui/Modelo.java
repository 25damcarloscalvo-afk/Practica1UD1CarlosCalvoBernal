package com.carlos.accesoriospc.gui;

import com.carlos.accesoriospc.base.Perifericos;

import java.util.ArrayList;

public class Modelo {
    public ArrayList<Perifericos> listaPerifericos;

    public Modelo() {
        listaPerifericos = new ArrayList<Perifericos>();
    }

    public ArrayList<Perifericos> obtenerPerifericos() {
        return listaPerifericos;
    }
}

package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public class Teclado extends  Perifericos{
    public double precio;

    public Teclado() {

    }

    public Teclado(int idPeriferico, String marca, String modelo, LocalDate fechaCompra, double precio) {
        super(idPeriferico, marca, modelo, fechaCompra);
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Teclado: " + getIdPeriferico() + ", " + getMarca() + ", " + getModelo() + ", " + getPrecio();
    }
}

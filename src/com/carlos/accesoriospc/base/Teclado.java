package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public class Teclado extends  Perifericos{
    public double precio;

    public Teclado() {

    }

    public Teclado(int idPeriferico, boolean inalambrico, String fabricante, LocalDate fechaCompra, double precio) {
        super(idPeriferico, inalambrico, fabricante, fechaCompra);
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
        return "Teclado: " + getIdPeriferico() + ", " + getFechaCompra() + ", " + isInalambrico() + ", " + getPrecio();
    }
}

package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public class Raton extends  Perifericos{
    private int numeroExistencias;

    public Raton() {

    }

    public Raton(int idPeriferico, boolean inalambrico, String fabricante, int numeroExistencias, LocalDate fechaCompra, double precio) {
        super(idPeriferico, inalambrico, fabricante, fechaCompra, precio);
        this.numeroExistencias = numeroExistencias;
    }

    public int getNumeroExistencias() {
        return numeroExistencias;
    }

    public void setNumeroExistencias(int numeroExistencias) {
        this.numeroExistencias = numeroExistencias;
    }

    @Override
    public String toString() {
        return "Raton: " + getIdPeriferico() + ", " +
                getFabricante() + ", " + isInalambrico() + ", " +
                getFechaCompra() + ", " + getNumeroExistencias() + ", " + getPrecio();
    }
}

package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public class Raton extends  Perifericos{
    private int numeroExistencias;

    public Raton() {

    }

    public Raton(int idPeriferico, String marca, String modelo, LocalDate fechaCompra, int numeroExistencias) {
        super(idPeriferico, marca, modelo, fechaCompra);
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
        return "Raton: " + getIdPeriferico() + ", " + getMarca() + ", " + getModelo() + ", " + getNumeroExistencias();
    }
}

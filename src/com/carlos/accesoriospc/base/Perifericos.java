package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public abstract class Perifericos {
    private int idPeriferico;
    private boolean inalambrico;
    private String fabricante;
    private LocalDate fechaCompra;

    public Perifericos() {

    }

    public Perifericos(int idPeriferico, boolean inalambrico, String fabricante, LocalDate fechaCompra) {
        this.idPeriferico = idPeriferico;
        this.inalambrico = inalambrico;
        this.fabricante = fabricante;
        this.fechaCompra = fechaCompra;
    }

    public int getIdPeriferico() {
        return idPeriferico;
    }

    public void setIdPeriferico(int idPeriferico) {
        this.idPeriferico = idPeriferico;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}

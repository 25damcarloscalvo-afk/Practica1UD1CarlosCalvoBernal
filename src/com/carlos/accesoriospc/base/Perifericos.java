package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public abstract class Perifericos {
    private int idPeriferico;
    private boolean inalambrico;
    private String fabricante;
    private LocalDate fechaCompra;
    private double precio;
    String opinion;

    public Perifericos() {

    }

    public Perifericos(int idPeriferico, boolean inalambrico, String fabricante, LocalDate fechaCompra, double precio, String opinion) {
        this.idPeriferico = idPeriferico;
        this.inalambrico = inalambrico;
        this.fabricante = fabricante;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.opinion = opinion;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getInalambricoTexto() {
        if (inalambrico) {
            return "Si";
        }else {
            return "no";
        }
    }
}

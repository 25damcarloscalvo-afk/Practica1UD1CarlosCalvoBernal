package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public abstract class Perifericos {
    private int idPeriferico;
    private String marca;
    private String modelo;
    private LocalDate fechaCompra;

    public Perifericos() {

    }

    public Perifericos(int idPeriferico, String marca, String modelo, LocalDate fechaCompra) {
        this.idPeriferico = idPeriferico;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaCompra = fechaCompra;
    }

    public int getIdPeriferico() {
        return idPeriferico;
    }

    public void setIdPeriferico(int idPeriferico) {
        this.idPeriferico = idPeriferico;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}

package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public class Auriculares extends Perifericos{
    private String tipoConexion;

    public String getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipoConexion = tipoConexion;
    }
    public Auriculares() {

    }

    public Auriculares(int idPeriferico, boolean inalambrico, String fabricante,
                       LocalDate fechaCompra, double precio, String opinion, String tipoConexion) {
        super(idPeriferico, inalambrico, fabricante, fechaCompra, precio, opinion);
        this.tipoConexion = tipoConexion;
    }

    @Override
    public String toString() {
        return "Auriculares: " + getIdPeriferico() + ", " +
                getFechaCompra() + ", " + getInalambricoTexto() + ", " + getPrecio() + ", " + getTipoConexion() + ", " + opinion;
    }
}

package com.carlos.accesoriospc.base;

import java.time.LocalDate;

public class Teclado extends  Perifericos{
    public int peso;

    public Teclado() {

    }

    public Teclado(int idPeriferico, boolean inalambrico, String fabricante, LocalDate fechaCompra, double precio, int peso, String opinion) {
        super(idPeriferico, inalambrico, fabricante, fechaCompra, precio, opinion);
        this.peso = peso;
    }

    public int getpeso() {
        return peso;
    }

    public void setpeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Teclado: " + getIdPeriferico() + ", " +
                getFechaCompra() + ", " + getInalambricoTexto() + ", " + getPrecio() + ", " + getpeso() + ", " + opinion;
    }
}

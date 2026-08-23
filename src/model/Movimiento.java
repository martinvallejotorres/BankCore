package model;

import java.time.LocalDateTime;

public class Movimiento {

    private LocalDateTime fecha;
    private String tipo;
    private double monto;
    private String descripcion;
    private double saldoResultante;


    public Movimiento(String tipo, double monto, String descripcion, double saldoResultante) {
        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.saldoResultante = saldoResultante;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getSaldoResultante() {
        return saldoResultante;
    }


    @Override
    public String toString() {
        return "\n------------------------------" +
                "\nFecha: " + fecha +
                "\nTipo: " + tipo +
                "\nMonto: $" + monto +
                "\nDescripcion: " + descripcion +
                "\nSaldo resultante: $" + saldoResultante;
    }

}

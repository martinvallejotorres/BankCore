package model;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Cuenta {

    private UUID numero;
    private double saldo;
    private Cliente titular;
    private ArrayList<Movimiento> movimientos;
    private boolean estado;


    public Cuenta(Cliente titular){
        this.numero = UUID.randomUUID();
        this.saldo = 0;
        this.titular = titular;
        this.movimientos = new ArrayList<>();
        this.estado = true;
    }

    public double getSaldo() {
        return saldo;
    }

    public UUID getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public boolean getEstado() {
        return estado;
    }



    public  void depositar(double monto){

        if (!estado) {
            System.out.println("La cuenta esta bloqueada.");
            return;
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0.");
            return;
        }

        saldo += monto;

        Movimiento movimiento = new Movimiento(
                "DEPOSITO",
                monto,
                "Deposito en cuenta",
                saldo
        );

        agregarMovimiento(movimiento);
    }

    public void retirar (double monto){
        if (!estado) {
            System.out.println("La cuenta esta bloqueada.");
            return;
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0.");
            return;
        }

        if (saldo >= monto) {

            saldo -= monto;

            Movimiento movimiento = new Movimiento(
                    "RETIRO",
                    monto,
                    "Retiro de cuenta",
                    saldo
            );

            agregarMovimiento(movimiento);

        } else {

            System.out.println(
                    "No dispones del monto para retirar $" + monto
            );
        }
    }

    public double consultarSaldo(){
        return saldo;
    }

    protected void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    public void bloquear() {
        estado = false;
    }

    public void desbloquear() {
        estado = true;
    }

    @Override
    public String toString(){
        return "\n------------------------------" +
                "\nNumero de Cuenta: " + numero +
                "\nTitular: " + titular.getNombreCompleto() +
                "\nSaldo: $" + saldo +
                "\nEstado de la cuenta: " + (estado ? "Activa" : "Bloqueada");
    }

}

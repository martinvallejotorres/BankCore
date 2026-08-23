package model;

public class CuentaCorriente extends Cuenta{

    private double limiteDescubierto;

    public CuentaCorriente(Cliente titular, double limiteDescubierto) {
        super(titular);
        this.limiteDescubierto = limiteDescubierto;
    }

    public double getLimiteDescubierto() {
        return limiteDescubierto;
    }

    public void setLimiteDescubierto(double limiteDescubierto) {
        this.limiteDescubierto = limiteDescubierto;
    }
}

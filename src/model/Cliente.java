package model;

import java.util.ArrayList;

public class Cliente {


    private static int contador = 1;
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private ArrayList<Cuenta> cuentas;


    public Cliente(String nombre, String apellido, int dni) {
        this.id = contador++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuentas = new ArrayList<>();
    }


    public int getDni() {
        return dni;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }

    @Override
    public String toString(){
        return "Numero de Cliente: " + id +
                "\nNombre del titular: " + getNombreCompleto() +
                "\nDNI: " + dni +
                "\nCantidad de cuentas: " + cuentas.size();
    }







}

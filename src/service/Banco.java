package service;

import model.Cliente;
import model.Cuenta;

import java.util.ArrayList;
import java.util.UUID;

public class Banco {
    private String nombre;
    private ArrayList<Cliente> clientes;
    private ArrayList<Cuenta> cuentas;


    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }


    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }


    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }


    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);

        cuenta.getTitular().agregarCuenta(cuenta);
    }


    public Cliente buscarClientePorDni(int dni) {

        for (Cliente cliente : clientes) {

            if (cliente.getDni() == dni) {
                return cliente;
            }
        }

        return null;
    }


    public Cuenta buscarCuentaPorNumero(UUID numero) {

        for (Cuenta cuenta : cuentas) {

            if (cuenta.getNumero().equals(numero)) {
                return cuenta;
            }
        }

        return null;
    }


    public void listarClientes() {

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }


    public void listarCuentas() {

        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }


    @Override
    public String toString() {
        return "Banco: " + nombre +
                "\nCantidad de clientes: " + clientes.size() +
                "\nCantidad de cuentas: " + cuentas.size();
    }
}

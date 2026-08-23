
import model.*;
import service.Banco;

void main() {

    Scanner scanner = new Scanner(System.in);

    Banco banco = new Banco("BankCore");

    int opcion;

    do {

        System.out.println("\n==============================");
        System.out.println("          BANKCORE");
        System.out.println("==============================");
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear cuenta");
        System.out.println("3. Depositar");
        System.out.println("4. Retirar");
        System.out.println("5. Consultar saldo");
        System.out.println("6. Ver movimientos");
        System.out.println("7. Listar clientes");
        System.out.println("8. Listar cuentas");
        System.out.println("9. Bloquear cuenta");
        System.out.println("10. Desbloquear cuenta");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opcion: ");

        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {

            case 1:
                crearCliente(scanner, banco);
                break;

            case 2:
                crearCuenta(scanner, banco);
                break;

            case 3:
                depositar(scanner, banco);
                break;

            case 4:
                retirar(scanner, banco);
                break;

            case 5:
                consultarSaldo(scanner, banco);
                break;

            case 6:
                verMovimientos(scanner, banco);
                break;

            case 7:
                System.out.println("\n--- CLIENTES ---");
                banco.listarClientes();
                break;

            case 8:
                System.out.println("\n--- CUENTAS ---");
                banco.listarCuentas();
                break;

            case 9:
                bloquearCuenta(scanner, banco);
                break;

            case 10:
                desbloquearCuenta(scanner, banco);
                break;

            case 0:
                System.out.println("Cerrando BankCore...");
                break;

            default:
                System.out.println("Opcion invalida.");
        }

    } while (opcion != 0);

    scanner.close();
}


public static void crearCliente(Scanner scanner, Banco banco) {

    System.out.println("\n--- CREAR CLIENTE ---");

    System.out.print("Nombre: ");
    String nombre = scanner.nextLine();

    System.out.print("Apellido: ");
    String apellido = scanner.nextLine();

    System.out.print("DNI: ");
    int dni = scanner.nextInt();
    scanner.nextLine();

    Cliente cliente = new Cliente(nombre, apellido, dni);

    banco.agregarCliente(cliente);

    System.out.println("\nCliente creado correctamente.");
    System.out.println(cliente);
}


public static void crearCuenta(Scanner scanner, Banco banco) {

    System.out.println("\n--- CREAR CUENTA ---");

    System.out.print("DNI del cliente: ");
    int dni = scanner.nextInt();
    scanner.nextLine();

    Cliente cliente = banco.buscarClientePorDni(dni);

    if (cliente == null) {
        System.out.println("No existe un cliente con ese DNI.");
        return;
    }

    System.out.println("\nTipo de cuenta:");
    System.out.println("1. Caja de Ahorro");
    System.out.println("2. Cuenta Corriente");

    System.out.print("Seleccione: ");
    int tipo = scanner.nextInt();
    scanner.nextLine();

    Cuenta cuenta;

    switch (tipo) {

        case 1:
            cuenta = new CajaAhorro(cliente);
            break;

        case 2:
            System.out.print("Limite de descubierto: $");
            double descubierto = scanner.nextDouble();
            scanner.nextLine();

            cuenta = new CuentaCorriente(cliente, descubierto);
            break;

        default:
            System.out.println("Tipo de cuenta invalido.");
            return;
    }

    banco.agregarCuenta(cuenta);

    System.out.println("\nCuenta creada correctamente.");
    System.out.println(cuenta);
}


public static void depositar(Scanner scanner, Banco banco) {

    System.out.println("\n--- DEPOSITAR ---");

    Cuenta cuenta = pedirCuenta(scanner, banco);

    if (cuenta == null) {
        return;
    }

    System.out.print("Monto a depositar: $");
    double monto = scanner.nextDouble();
    scanner.nextLine();

    cuenta.depositar(monto);

    System.out.println("Saldo actual: $" + cuenta.getSaldo());
}


public static void retirar(Scanner scanner, Banco banco) {

    System.out.println("\n--- RETIRAR ---");

    Cuenta cuenta = pedirCuenta(scanner, banco);

    if (cuenta == null) {
        return;
    }

    System.out.print("Monto a retirar: $");
    double monto = scanner.nextDouble();
    scanner.nextLine();

    cuenta.retirar(monto);

    System.out.println("Saldo actual: $" + cuenta.getSaldo());
}


public static void consultarSaldo(Scanner scanner, Banco banco) {

    System.out.println("\n--- CONSULTAR SALDO ---");

    Cuenta cuenta = pedirCuenta(scanner, banco);

    if (cuenta == null) {
        return;
    }

    System.out.println("Titular: " +
            cuenta.getTitular().getNombreCompleto());

    System.out.println("Saldo: $" +
            cuenta.consultarSaldo());

    System.out.println(
            "Estado: " +
                    (cuenta.getEstado() ? "Activa" : "Bloqueada")
    );
}


public static void verMovimientos(Scanner scanner, Banco banco) {

    System.out.println("\n--- MOVIMIENTOS ---");

    Cuenta cuenta = pedirCuenta(scanner, banco);

    if (cuenta == null) {
        return;
    }

    if (cuenta.getMovimientos().isEmpty()) {
        System.out.println("La cuenta no tiene movimientos.");
        return;
    }

    for (Movimiento movimiento : cuenta.getMovimientos()) {
        System.out.println(movimiento);
    }
}


public static void bloquearCuenta(Scanner scanner, Banco banco) {

    System.out.println("\n--- BLOQUEAR CUENTA ---");

    Cuenta cuenta = pedirCuenta(scanner, banco);

    if (cuenta == null) {
        return;
    }

    if (!cuenta.getEstado()) {
        System.out.println("La cuenta ya esta bloqueada.");
        return;
    }

    cuenta.bloquear();

    System.out.println("Cuenta bloqueada correctamente.");
}


public static void desbloquearCuenta(
        Scanner scanner,
        Banco banco) {

    System.out.println("\n--- DESBLOQUEAR CUENTA ---");

    Cuenta cuenta = pedirCuenta(scanner, banco);

    if (cuenta == null) {
        return;
    }

    if (cuenta.getEstado()) {
        System.out.println("La cuenta ya esta activa.");
        return;
    }

    cuenta.desbloquear();

    System.out.println("Cuenta desbloqueada correctamente.");
}


public static Cuenta pedirCuenta(
        Scanner scanner,
        Banco banco) {

    System.out.print("Numero de cuenta: ");
    String numeroTexto = scanner.nextLine();

    try {

        UUID numero = UUID.fromString(numeroTexto);

        Cuenta cuenta =
                banco.buscarCuentaPorNumero(numero);

        if (cuenta == null) {
            System.out.println(
                    "No existe una cuenta con ese numero."
            );
        }

        return cuenta;

    } catch (IllegalArgumentException e) {

        System.out.println(
                "El numero de cuenta ingresado no es valido."
        );

        return null;
    }
}


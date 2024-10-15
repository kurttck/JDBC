import java.util.Scanner;

import servicios.ClienteServicio;

public class Menu {

    private ClienteServicio clienteServicio;
    private Scanner scan;

    public Menu() {
        this.clienteServicio = new ClienteServicio();
        this.scan = new Scanner(System.in);
    }

    public void mostrarMenu() throws Exception {
        int option = 0;
        String menu = """
                Menuu de Opciones
                1. Crear nuevo cliente
                2. Listar Clientes
                3. Actualizar Clientes
                4. Eliminar Cliente
                0. Salir.
                """;

        do {
            System.out.println(menu);
            option = scan.nextInt();

            switch (option) {
                case 1:
                    crearCliente();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (option != 0);

    }

    private void crearCliente() throws Exception {

        System.out.println("Creando Cliente");
        System.out.println("Ingresa Nombre del Cliente");
        scan.nextLine();
        String nombreCliente = scan.nextLine();
        System.out.println("Ingresa Nombre del Contacto");
        String nombreContacto = scan.nextLine();
        System.out.println("Ingresa Apellido del Contacto");
        String apellidoContacto = scan.nextLine();
        System.out.println("Ingresa Telefono del Contacto");
        String telefono = scan.nextLine();

        clienteServicio.crearNuevoCliente(nombreCliente, nombreContacto, apellidoContacto, telefono, "", "", "", "", "",
                2, 0);
    }

}

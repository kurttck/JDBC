/* import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; */

//import persistencia.ClienteDAO;
//import persistencia.ProductoDAO;

//import java.util.ArrayList;

import entidades.Cliente;
import entidades.Pedido;
import servicios.ClienteServicio;
import servicios.PedidoServicio;

public class Main {

    /*
     * private static final String HOST = "127.0.0.1";
     * private static final String PORT = "3306";
     * private static final String DATABASE = "vivero";
     * private static final String USER = "root";
     * private static final String PASSWORD = "root";
     * private static final String ZONE =
     * "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
     * private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" +
     * DATABASE + ZONE;
     */

    public static void main(String[] args) throws Exception {
        /*
         * Connection conn = getConnection();
         * if (conn != null) {
         * System.out.println("Connected");
         * closeConnection(conn);
         * }
         */

        /*
         * ClienteDAO clientePrueba = new ClienteDAO();
         * ArrayList<Cliente> clientes = new ArrayList<>();
         * 
         * 
         * Cliente cliente1 = new Cliente(5, "LadyBug SAC", "Diana", "ML",
         * "985647133", "748586", "Por ahi",
         * "Huancayo", "Peru", "Hua158", 4, 1000.00);
         * 
         * System.out.println(cliente1);
         * 
         * clientePrueba.guardarCliente(cliente1);
         * 
         * clientes = clientePrueba.listarTodosLosClientes();
         * 
         * for (int i = 0; i < clientes.size(); i++) {
         * System.out.println("Contacto: " + clientes.get(i).getNombreContacto() + " "
         * + clientes.get(i).getApellidoContacto() + " | idCliente: "
         * + clientes.get(i).getIdCliente());
         * }
         * 
         * cliente1.toString();
         */

        /* clientePrueba.borrarCliente(20); */

        /* -------------- PRODUCTO -------------------------------------- */
        /*
         * var productoPrueba = new ProductoDAO();
         * 
         * productoPrueba.eliminarProductoPorCodigo(50);
         */

        /* ------------------- SERVICIO ---------------------------- */

        var clienteServicio = new ClienteServicio();

        /*
         * Cliente cliente = clienteServicio.crearNuevoCliente(5, "Huancayito Park",
         * "Diane", "ML",
         * "985647133", "748586", "Por ahi",
         * "Huancayo", "Peru", "Hua158", 4, 1000.00);
         * 
         * System.out.println(cliente);
         */

        /*
         * Cliente clienteprueba = clienteServicio.buscarClientePorCodigo(24);
         * System.out.println(clienteprueba);
         */

        var pedidoServicio = new PedidoServicio();

        Pedido pedidoPrueba = pedidoServicio.crearNuevoPedido(41, "2024-10-09", "2024-12-10", "2024-12-01", "Entregado",
                "Esta muy bonito el producto", 19);

        System.out.println(pedidoPrueba.getComentarios());
    }

    /*
     * public static Connection getConnection() {
     * try {
     * Class.forName("com.mysql.cj.jdbc.Driver");
     * return DriverManager.getConnection(URL, USER, PASSWORD);
     * } catch (ClassNotFoundException e) {
     * System.err.println("JDBC Driver not found" + e.getMessage());
     * } catch (SQLException e) {
     * System.err.println("Failed to create connection" + e.getMessage());
     * }
     * return null;
     * }
     */

    /*
     * public static void closeConnection(Connection conn) {
     * try {
     * if (conn != null) {
     * conn.close();
     * System.out.println("Connection closed");
     * }
     * } catch (SQLException e) {
     * System.err.println("Failed to close connection" + e.getMessage());
     * }
     * }
     */
}

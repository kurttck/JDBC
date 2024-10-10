import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

public class App {
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "vivero";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + ZONE;

    public static void main(String[] args) throws Exception {
        Connection conn = getConnection();
        if (conn != null) {
            // buscarClientes(conn);
            // buscarClientesPorCodigo(conn, "12");
            // buscarClientesPorEmpleado(conn, "2");
            // getProductosParaReparar(conn, 16);
            // getProductosGama(conn, "Frutales");
            // getPedidosPorCliente(conn, 5);
            getPedidosPorEstado(conn, "Entregado");
            CloseConnection(conn);
        }

    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
        }
        return null;
    }

    public static void CloseConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to MySQL has been closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error to close connection");
        }
    }

    public static void buscarClientes(Connection conn) {
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente ";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + ". " + nombre + " " + apellido + " - " + telefono);
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void buscarClientesPorCodigo(Connection conn, String codigo) {
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente WHERE id_cliente=" + codigo;
        try {
            Statement stmr = conn.createStatement();
            ResultSet rs = stmr.executeQuery(sql);

            if (rs.next()) {
                String name = rs.getString("nombre_contacto");
                String lastName = rs.getString("apellido_contacto");
                String phone = rs.getString("telefono");
                String mensaje = """
                        El cliente solicitado con codigo %s es:
                        nombre: %s %s
                        telefono: %s
                        """;
                System.out.println(mensaje.formatted(codigo, name, lastName, phone));
            } else {
                System.out.println("No se encontro el cliente con el codigo: " + codigo);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void buscarClientesPorEmpleado(Connection conn, String codempleado) {
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente WHERE id_empleado="
                + codempleado;

        try {
            Statement stmr = conn.createStatement();
            ResultSet rs = stmr.executeQuery(sql);
            int count = 0;
            System.out.println("Listado de clientes del empleado con id_empleado: " + codempleado);

            if (!rs.next()) {
                System.out.println("No se encontraron registros");
                return;
            }

            while (rs.next()) {
                String name = rs.getString("nombre_contacto");
                String lastName = rs.getString("apellido_contacto");
                String phone = rs.getString("telefono");
                String mensaje = """
                        %s. nombre: %s %s - telefono: %s
                        """;
                count++;
                System.out.println(mensaje.formatted(count, name, lastName, phone));

            }

            stmr.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public static void getProductosParaReparar(Connection conn, int puntoReposicion) {
        String sql = "SELECT codigo_producto, nombre, proveedor, cantidad_en_stock FROM producto WHERE cantidad_en_stock<"
                + puntoReposicion;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            System.out.println("Listado de productos por reponer");

            while (rs.next()) {
                String name = rs.getString("nombre");
                String codigo_producto = rs.getString("codigo_producto");
                String proveedor = rs.getString("proveedor");
                String cantidad_en_stock = rs.getString("cantidad_en_stock");
                String mensaje = """
                        %s. codigo: %s - nombre: %s - proveedor: %s - cantidad en stock: %s
                        """;

                count++;
                System.out.println(mensaje.formatted(count, codigo_producto, name, proveedor, cantidad_en_stock));
            }

            stmt.close();
            rs.close();

        } catch (SQLException ex) {
        }

    }

    public static void getProductosGama(Connection conn, String gama) {
        String sql = "SELECT p.codigo_producto, p.nombre, g.id_gama, gama from producto p INNER JOIN gama_producto g ON p.id_gama = g.id_gama where g.gama='"
                + gama + "'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            System.out.println("Listado de productos por gama");

            while (rs.next()) {
                String codigoProducto = rs.getString("codigo_producto");
                String nombreProduct = rs.getString("nombre");
                String idGama = rs.getString("id_gama");
                String nombreGama = rs.getString("gama");

                count++;
                System.out.println(
                        count + ". " + codigoProducto + " - " + nombreProduct + " - " + idGama + " - " + nombreGama);
            }

            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void getPedidosPorCliente(Connection conn, int idCliente) {
        String sql = "select p.id_pedido, p.codigo_pedido, p.fecha_entrega, c.nombre_cliente from pedido p inner join cliente c on p.id_cliente = c.id_cliente where c.id_cliente = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idCliente);

            ResultSet rs = pstmt.executeQuery();
            int count = 0;

            while (rs.next()) {
                String idPedido = rs.getString("id_pedido");
                String codigoPedido = rs.getString("codigo_pedido");
                String fechaEntrega = rs.getString("fecha_entrega");
                String nombreCliente = rs.getString("nombre_cliente");
                count++;
                System.out.println(
                        count + ". " + idPedido + " - " + codigoPedido + " - " + fechaEntrega + " - " + nombreCliente);
            }

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    public static void getPedidosPorEstado(Connection conn, String estado) {
        String sql = """
                select id_pedido, codigo_pedido, estado
                from pedido
                where estado = ?
                """;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, estado);

            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                String idPedido = rs.getString("id_pedido");
                String codigoPedido = rs.getString("codigo_pedido");
                String estate = rs.getString("estado");
                count++;
                System.out.println(count + ". " + idPedido + " - " + codigoPedido + " - " + estate);
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
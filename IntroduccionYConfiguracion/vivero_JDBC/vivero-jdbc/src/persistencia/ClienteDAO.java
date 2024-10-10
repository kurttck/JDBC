package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;

public class ClienteDAO extends DAO {

    public int getPosicionCliente() throws Exception {
        try {
            String sql = "select count(*) as count from cliente";

            consultarDataBase(sql);
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("count") + 1;
            }

            return number;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente != null) {
            int position = getPosicionCliente();

            String sql = "INSERT INTO cliente(id_cliente, codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, ciudad, region, pais, codigo_postal, id_empleado, limite_credito)"
                    + " VALUES(" +
                    position + ", " +
                    position + ", '" +
                    cliente.getNombreCliente() + "', '" +
                    cliente.getNombreContacto() + "', '" +
                    cliente.getApellidoContacto() + "', '" +
                    cliente.getTelefono() + "', '" +
                    cliente.getFax() + "', '" +
                    cliente.getCiudad() + "', '" +
                    cliente.getRegion() + "', '" +
                    cliente.getPais() + "', '" +
                    cliente.getCodigoPostal() + "', " +
                    cliente.getIdEmpleado() + ", " +
                    cliente.getLimiteCredito() + ");";

            try {
                insertarModificarEliminarDataBase(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public ArrayList<Cliente> listarTodosLosClientes() throws Exception {

        try {
            String sql = "SELECT nombre_contacto, apellido_contacto, id_cliente FROM cliente;";

            consultarDataBase(sql);
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                cliente.setIdCliente(rs.getInt("id_cliente"));
                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public void borrarCliente(int id) throws Exception {
        String sql = "DELETE FROM cliente WHERE id_cliente=" + id;
        try {
            insertarModificarEliminarDataBase(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        try {
            String sql = "select * from cliente where codigo_cliente=" + codigo;
            consultarDataBase(sql);
            var cliente = new Cliente();
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setCodigoCliente(rs.getInt("codigo_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFax(rs.getString("fax"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setRegion(rs.getString("region"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigoPostal(rs.getString("codigo_postal"));
                cliente.setIdEmpleado(rs.getInt("id_empleado"));
                cliente.setLimiteCredito(rs.getDouble("limite_credito"));
            }
            return cliente;

        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Cliente> listarClientesVinculadosIdEmpleado(int id) throws Exception {
        try {
            String sql = "select * from cliente where id_empleado=" + id;
            consultarDataBase(sql);
            ArrayList<Cliente> clientes = new ArrayList<>();

            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                cliente.setIdCliente(rs.getInt("id_cliente"));
                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void incrementarLimiteDeCredito() throws Exception {
        try {
            String sql = "update cliente set limite_credito = limite_credito*1.15";
            insertarModificarEliminarDataBase(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}

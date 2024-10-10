package servicios;

import java.util.ArrayList;

import entidades.Cliente;
import persistencia.ClienteDAO;

public class ClienteServicio {
    private ClienteDAO cd;

    public ClienteServicio() {
        this.cd = new ClienteDAO();
    }

    public Cliente crearNuevoCliente(String nombre, String nombreContacto, String apellidoContacto,
            String telefono,
            String fax, String ciudad, String region, String pais, String codigoPostal, int idEmpleado,
            double limiteCredito) throws Exception {

        validacionesNyA(nombre, apellidoContacto);

        Cliente cliente = new Cliente(nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, region,
                pais,
                codigoPostal, idEmpleado, limiteCredito);

        cd.guardarCliente(cliente);

        return cliente;
    }

    public void validacionesNyA(String nombre, String apellido) throws Exception {
        if (nombre == null) {
            throw new Exception("El nombre no puede ser nulo");
        }
        if (apellido == null) {
            throw new Exception("El apellido no puede ser nulo");
        }
    }

    public ArrayList<Cliente> listarTodosLosClientes() throws Exception {
        return cd.listarTodosLosClientes();
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        validacionCodigo(codigo);
        return cd.buscarClientePorCodigo(codigo);
    }

    public void validacionCodigo(int codigo) throws Exception {

        if (codigo <= 0) {
            throw new Exception("El codigo no puede ser negativo");
        }
        int positionActual = cd.getPosicionCliente();

        if (positionActual <= codigo) {
            throw new Exception("El cliente no existe");
        }

    }

}

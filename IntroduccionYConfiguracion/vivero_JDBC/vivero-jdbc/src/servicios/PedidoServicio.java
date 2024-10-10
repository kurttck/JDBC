package servicios;

import entidades.Pedido;
import persistencia.PedidoDAO;

public class PedidoServicio {

    private PedidoDAO pd;

    public PedidoServicio() {
        this.pd = new PedidoDAO();
    }

    public Pedido crearNuevoPedido(int codigoPedido, String fechaPedido, String fechaEsperada, String fechaEntregada,
            String estado,
            String comentarios, int idCliente) throws Exception {

        validacionPedido(idCliente, fechaPedido);

        Pedido pedido = new Pedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntregada, estado, comentarios,
                idCliente);

        pd.guardarPedido(pedido);

        return pedido;
    }

    public void validacionPedido(int codigoPedido, String fechaPedido) throws Exception {
        if (codigoPedido < 0) {
            throw new Exception("El pedido no puede ser negativo");
        }
        if (fechaPedido == null) {
            throw new Exception("La fecha no puede ser nula");
        }
    }
}

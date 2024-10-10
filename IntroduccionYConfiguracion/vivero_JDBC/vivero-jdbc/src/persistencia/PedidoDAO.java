package persistencia;

import java.sql.SQLException;

import entidades.Pedido;

public class PedidoDAO extends DAO {

    public int getPosicionPedido() throws Exception {
        try {
            String sql = "select max(id_pedido) as max from pedido";

            consultarDataBase(sql);
            int number = 0;
            if (rs.next()) {
                number = rs.getInt("max") + 1;
            }

            return number;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void guardarPedido(Pedido pedido) throws Exception {
        int position = getPosicionPedido();
        try {
            String sql = "insert into pedido(id_pedido, codigo_pedido, fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios, id_cliente) values("
                    +
                    position + "," +
                    pedido.getCodigoPedido() + ", '" +
                    pedido.getFechaPedido() + "', '" +
                    pedido.getFechaEsperada() + "', '" +
                    pedido.getFechaEntrega() + "', '" +
                    pedido.getEstado() + "','" +
                    pedido.getComentarios() + "'," +
                    pedido.getIdCliente() + ");";

            insertarModificarEliminarDataBase(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

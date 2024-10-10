package persistencia;

import java.sql.SQLException;

public class ProductoDAO extends DAO {

    /*
     * private int getPosicionProducto() throws Exception {
     * try {
     * String sql = "select count(*) as count from producto";
     * consultarDataBase(sql);
     * 
     * int number = 0;
     * if (rs.next()) {
     * number = rs.getInt("count") + 1;
     * }
     * 
     * return number;
     * 
     * } catch (SQLException e) {
     * System.out.println(e.getMessage());
     * return 0;
     * }
     * }
     */

    public void eliminarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE id_producto=" + codigo;
            insertarModificarEliminarDataBase(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

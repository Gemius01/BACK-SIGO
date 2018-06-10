package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Item;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Item
public interface ItemRepository extends JpaRepository<Item, Long> {

    //Select * from item where tipo.nombre = "Herramienta";
    //@Query("select modelo from Item where id_subcategoria=(select id from Tipo where nombre='Default')")
    /*@Query(value = "select * from Item where nombre <> ?1", nativeQuery = true)
    List<Item> listaTodo(String algo);*/
    @Query(value = "select * from item where id = any "
            + "(select id_item from detalle_item where id_bodega = any"
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1)))", nativeQuery = true)
    List<Item> listaTodo(long id);

    @Query(value = "select * from Item where id_subcategoria = "
            + "(select id from Sub_Categoria where id_categoria = "
            + "(select id from Categoria where id_tipo = "
            + "(select id from Tipo where nombre ='Insumo'))) "
            + "and stock <= stock_critico", nativeQuery = true)
    List<Item> listaReporteInsumoBajo();

    /*
    SELECT PedidosPendientes.Nombre FROM PedidosPendientes GROUP BY PedidosPendientes.Nombre HAVING SUM(PedidosPendientes.Cantidad < ( SELECT Productos.Stock FROM Productos WHERE Productos.IdProducto = PedidosPendientes.IdProducto ) )
     */
    @Query(value = "select * from item where id_subcategoria = "
            + "(select * from sub_categoria where id_categoria = "
            + "(select * from categoria where id_tipo = "
            + "(select * from tipo where nombre = ?1)))", nativeQuery = true)
    List<Item> ItemsByTipos(String nombre);

}

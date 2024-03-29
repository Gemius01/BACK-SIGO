package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Orden_Compra;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Orden_Compra
public interface Orden_CompraRepository extends JpaRepository<Orden_Compra, Long> {

    @Query(value = "select * from orden_compra where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1))", nativeQuery = true)
    List<Orden_Compra> listaTodo(long id);

    @Query(value = "SELECT * FROM orden_compra"
            + " left JOIN Compra on orden_compra.id=compra.id_orden_compra"
            + " WHERE Compra.id_orden_compra IS NULL", nativeQuery = true)
    List<Orden_Compra> listaReporteOrdenesPendientes();

}

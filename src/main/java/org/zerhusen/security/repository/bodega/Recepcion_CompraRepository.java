package org.zerhusen.security.repository.bodega;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.zerhusen.model.bodega.Recepcion_Compra;

//Interfaz de la base de datos con el controllador Recepcion Compra
public interface Recepcion_CompraRepository extends JpaRepository<Recepcion_Compra, Long>{
	
    @Query(value = "select * from recepcion_compra where id_compra = any "
            + "(select id from compra where id_orden_compra = any "
            + "(select id from orden_compra where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1))))", nativeQuery = true)
    List<Recepcion_Compra> listaTodo(long id);
}
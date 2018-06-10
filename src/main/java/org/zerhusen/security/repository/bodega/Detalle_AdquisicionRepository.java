package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Detalle_Adquisicion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Detalle_Adquisicion
public interface Detalle_AdquisicionRepository extends JpaRepository<Detalle_Adquisicion, Long>{
    
    @Query(value = "select * from detalle_adquisicion where id_orden_compra = ?1", nativeQuery = true)
    List<Detalle_Adquisicion> adquisicionDetail(long id);
    
    @Query(value = "select * from detalle_adquisicion where id_orden_compra= any "
            + "(select id from orden_compra where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1)))", nativeQuery = true)
    List<Detalle_Adquisicion> listaTodo(long id);
}
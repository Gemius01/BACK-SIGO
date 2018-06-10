package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Cotizacion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Cotizacion
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long>{
	
    @Query(value = "select * from cotizacion where orden_compra = any "
            + "(select id from orden_compra where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = 1?)))", nativeQuery = true)
    List<Cotizacion> listaTodo(long id);
    
    @Query(value = "select * from cotizacion where orden_compra = ?1", nativeQuery = true)
    List<Cotizacion> CotizacionByOrden(long id);
    
    @Query(value = "select * from cotizacion WHERE orden_compra = (select id from orden_compra WHERE cotizable = true)", nativeQuery = true)
    List<Cotizacion> CotizacionByOrdenTrue();
    
}
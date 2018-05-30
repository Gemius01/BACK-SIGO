package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Cotizacion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Cotizacion
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long>{
	
    @Query(value = "select * from Cotizacion where rut_proveedor <> '11111111-1' ORDER BY `cotizacion`.`id` DESC", nativeQuery = true)
    List<Cotizacion> listaTodo();
    
    @Query(value = "select * from cotizacion where orden_compra = ?1", nativeQuery = true)
    List<Cotizacion> CotizacionByOrden(long id);
    
    @Query(value = "select * from cotizacion WHERE orden_compra = (select id from orden_compra WHERE cotizable = true)", nativeQuery = true)
    List<Cotizacion> CotizacionByOrdenTrue();
    
}
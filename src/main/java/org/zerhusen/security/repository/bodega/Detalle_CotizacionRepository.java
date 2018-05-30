package org.zerhusen.security.repository.bodega;

import org.zerhusen.model.bodega.Detalle_Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface Detalle_CotizacionRepository extends JpaRepository<Detalle_Cotizacion, Long>{
	
//    @Query(value = "select * from categoria where nombre <> 'CatDefault' ORDER BY `categoria`.`id` DESC", nativeQuery = true)
//    List<Categoria> listaTodo();
//    
    @Query(value = "select * from detalle_cotizacion where id_cotizacion = ?1", nativeQuery = true)
    List<Detalle_Cotizacion> DelailCotizable(long id);
}
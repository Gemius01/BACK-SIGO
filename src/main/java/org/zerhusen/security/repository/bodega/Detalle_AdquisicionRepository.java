package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Detalle_Adquisicion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Detalle_Adquisicion
public interface Detalle_AdquisicionRepository extends JpaRepository<Detalle_Adquisicion, Long>{
    
    @Query(value = "select * from detalle_adquisicion where id_orden_compra = ?1", nativeQuery = true)
    List<Detalle_Adquisicion> adquisicionDetail(long id);
}
package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Detalle_Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Detalle_Prestamo
public interface Detalle_PrestamoRepository extends JpaRepository<Detalle_Prestamo, Long>{
	
    @Query(value = "select * from detalle_prestamo where id_prestamo = ?1", nativeQuery = true)
    List<Detalle_Prestamo> DetailPrestamo(long id);
    
    @Query(value = "select * from detalle_prestamo where id_prestamo = (select id from prestamo where id = ?1 and devolucion = 1)", nativeQuery = true)
    List<Detalle_Prestamo> DetailByPrestados(long id);
    
}
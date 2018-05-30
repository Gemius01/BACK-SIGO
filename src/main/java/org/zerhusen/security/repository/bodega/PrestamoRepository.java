package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Prestamo
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    
    Prestamo findByrutSolicitante(String rutSolicitante);
    
    @Query(value = "select * from prestamo where devolucion = ?1 order by fecha asc", nativeQuery = true)
    List<Prestamo> devolucionBool(int bool);
    
}
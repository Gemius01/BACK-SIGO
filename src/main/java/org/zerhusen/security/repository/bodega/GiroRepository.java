package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Giro;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Giro
public interface GiroRepository extends JpaRepository<Giro, Long>{
	
    @Query(value = "select * from Giro where nombre <> 'Default' ORDER BY `giro`.`id` DESC", nativeQuery = true)
    List<Giro> listaTodo();
    
}
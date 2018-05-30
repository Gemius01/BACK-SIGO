package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Tipo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Tipo
public interface TipoRepository extends JpaRepository<Tipo, Long>{
	
    @Query(value = "select * from Tipo where nombre <> 'Default' ORDER BY `tipo`.`id` DESC", nativeQuery = true)
    List<Tipo> listaTodo();
    
}
package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Region;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Categoria
public interface RegionRepository extends JpaRepository<Region, Long>{
	
    @Query(value = "select * from Region where nombre <> 'RegionDefault'", nativeQuery = true)
    List<Region> listaTodo();
    
}

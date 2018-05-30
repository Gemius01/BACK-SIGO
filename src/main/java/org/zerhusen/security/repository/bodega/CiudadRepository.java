package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Ciudad;
import org.zerhusen.model.bodega.Region;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Categoria
public interface CiudadRepository extends JpaRepository<Ciudad, Long>{
	
    @Query(value = "select * from Ciudad where nombre <> 'CiudadDefault' ", nativeQuery = true)
    List<Ciudad> listaTodo();
    
    @Query(value = "select * from Ciudad where id_region = ?1 ", nativeQuery = true)
    List<Ciudad> listaCityByRegion(long id);
    
}

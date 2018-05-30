package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Pais;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Categoria
public interface PaisRepository extends JpaRepository<Pais, Long>{

    @Query(value = "select * from Pais where nombre <> 'PaisDefault'", nativeQuery = true)
    List<Pais> listaTodo();
    
}

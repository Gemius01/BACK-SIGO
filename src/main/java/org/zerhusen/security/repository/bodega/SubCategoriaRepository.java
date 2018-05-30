package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.SubCategoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador SubCategoria
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long>{
    
    @Query(value = "select * from Sub_Categoria where nombre <> 'SubCatDefault' ORDER BY `sub_categoria`.`id` DESC", nativeQuery = true)
    List<SubCategoria> listaTodo();
    
    @Query(value = "select * from sub_categoria where id_categoria = ?1", nativeQuery = true)
    List<SubCategoria> SubCategoriaByCategoria(long id);
	
}
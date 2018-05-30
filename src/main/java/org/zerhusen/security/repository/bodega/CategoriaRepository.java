package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Categoria
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
    @Query(value = "select * from categoria where nombre <> 'CatDefault' ORDER BY `categoria`.`id` DESC", nativeQuery = true)
    List<Categoria> listaTodo();
    
    @Query(value = "select * from categoria where id_tipo=(select id from tipo where nombre = ?1)", nativeQuery = true)
    List<Categoria> listaInsumosoHerramientas(String tipo);
    
}
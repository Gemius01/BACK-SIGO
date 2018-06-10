package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Categoria
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
    @Query(value = "select * from categoria where id_e_cliente = "
            + "(select rut from e_cliente where rut = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = 1?))", nativeQuery = true)
    List<Categoria> listaTodo(long id);
    
    @Query(value = "select * from categoria where id_tipo=(select id from tipo where nombre = ?1)", nativeQuery = true)
    List<Categoria> listaInsumosoHerramientas(String tipo);
    
}
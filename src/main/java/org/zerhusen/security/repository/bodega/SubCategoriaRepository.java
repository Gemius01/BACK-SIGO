package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.SubCategoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador SubCategoria
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long>{
    
    @Query(value = "select * from sub_categoria where id_categoria = any "
            + "( select id from categoria where id_e_cliente = any "
            + "(select rut from e_cliente where rut = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = 1?)))", nativeQuery = true)
    List<SubCategoria> listaTodo(long id);
    
    @Query(value = "select * from sub_categoria where id_categoria = ?1", nativeQuery = true)
    List<SubCategoria> SubCategoriaByCategoria(long id);
	
}
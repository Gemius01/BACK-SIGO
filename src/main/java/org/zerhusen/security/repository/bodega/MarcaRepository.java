package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Marca;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Categoria
public interface MarcaRepository extends JpaRepository<Marca, Long>{
 
    @Query(value = "select * from marca where nombre <> 'MarDefault' ORDER BY `marca`.`id` DESC", nativeQuery = true)
    List<Marca> listaTodo();
    
    @Query(value = "select * from marca where id = any "
            + "(select id_marca from detalle_marca where id_e_cliente = any "
            + "(select rut from e_cliente where rut = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1)))", nativeQuery = true)
    List<Marca> listaTodoXEmpresa(long id);
}
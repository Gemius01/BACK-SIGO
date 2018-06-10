/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.bodega;
import java.util.List;
import org.zerhusen.model.bodega.Detalle_Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Detalle_MarcaRepository extends JpaRepository<Detalle_Marca, Long>{
	
    @Query(value = "select * from detalle_marca where id_e_cliente = any "
            + "(select rut from e_cliente where rut = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1))", nativeQuery = true)
    List<Detalle_Marca> listaTodo(long id);
    
}

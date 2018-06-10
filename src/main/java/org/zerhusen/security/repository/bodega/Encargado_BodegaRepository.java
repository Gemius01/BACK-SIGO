/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.bodega;
import java.util.List;
import org.zerhusen.model.bodega.Encargado_Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Encargado_BodegaRepository extends JpaRepository<Encargado_Bodega, Long>{
    
    @Query(value = "select * from encargado_bodega where id_bodega = any (select id from bodega where id = 1 and id_e_cliente = (SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1))", nativeQuery = true)
    List<Encargado_Bodega> lisaTodo(long id);
    
}

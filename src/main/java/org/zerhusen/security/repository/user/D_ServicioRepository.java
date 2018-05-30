/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.user;

import org.zerhusen.model.user.D_Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ferenc
 */
public interface D_ServicioRepository extends JpaRepository<D_Servicio, Long> {

    @Query(value = "select * from d_servicio where id_servicio = (select id from servicio where id = ?1)", nativeQuery = true)
    List<D_Servicio> DetailByServicio(long id);
    
}

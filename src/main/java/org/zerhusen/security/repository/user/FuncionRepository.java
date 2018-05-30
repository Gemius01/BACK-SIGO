/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.user;

import org.zerhusen.model.user.Funcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ferenc
 */
public interface FuncionRepository extends JpaRepository<Funcion, Long> {
    
    @Query(value = "select * from funcion where id_seccion = ?1", nativeQuery = true)
    List<Funcion> FuncionBySeccion(long id);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.user;


import org.zerhusen.model.user.Seccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ferenc
 */
public interface SeccionRepository extends JpaRepository<Seccion, Long> {

       @Query(value = "select * from seccion where id_modulo = ?1", nativeQuery = true)
    List<Seccion> SeccionByModulo(long id);
}

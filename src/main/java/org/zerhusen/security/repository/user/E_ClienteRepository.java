/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.user;

import java.util.Optional;
import org.zerhusen.model.user.E_Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ferenc
 */
public interface E_ClienteRepository extends JpaRepository<E_Cliente, String> {

    //SELECT `e_cliente`.`rut`, `user`.`id` FROM `user`, `e_cliente` WHERE user.id =1
    @Query(value = "SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1", nativeQuery = true)
    Optional<E_Cliente> laempresa(long id);
}

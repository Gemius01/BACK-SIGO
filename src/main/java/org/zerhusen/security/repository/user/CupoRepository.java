/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.repository.user;

import org.zerhusen.model.user.Cupo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ferenc
 */
public interface CupoRepository extends JpaRepository<Cupo, Long> {

}

package org.zerhusen.security.repository.bodega;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.zerhusen.model.bodega.Salida;

//Interfaz de la base de datos con el controllador Salida Items
public interface SalidaRepository extends JpaRepository<Salida, Long>{

    @Query(value = "select * from salida where id_bodega = any (select id from bodega where id = 1 and id_e_cliente = (SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1))", nativeQuery = true)
    List<Salida> listaTodo(long id);
    
}
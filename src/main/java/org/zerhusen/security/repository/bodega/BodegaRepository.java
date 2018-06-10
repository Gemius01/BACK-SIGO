package org.zerhusen.security.repository.bodega;

import java.util.List;
import org.zerhusen.model.bodega.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Bodega
public interface BodegaRepository extends JpaRepository<Bodega, Long>{
	
    @Query(value = "select * from bodega where id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1)", nativeQuery = true)
    List<Bodega> listaTodo(long id);
    
}
package org.zerhusen.security.repository.bodega;


import java.util.List;
import org.zerhusen.model.bodega.Detalle_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Detalle_ItemRepository extends JpaRepository<Detalle_Item, Long>{
	
@Query(value = "select * from detalle_item where id_bodega = any (select id from bodega where id = 1 and id_e_cliente = (SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = 1?)) ", nativeQuery = true)
List<Detalle_Item> listaTodo(long id);
    
}
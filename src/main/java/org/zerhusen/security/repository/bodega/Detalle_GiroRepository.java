package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Detalle_Giro;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Detalle_Giro
public interface Detalle_GiroRepository extends JpaRepository<Detalle_Giro, Long>{
	
    @Query(value = "select * from detalle_giro where rut_proveedor = any "
            + "(select rut from proveedor where id_e_cliente = any "
            + "(select rut from e_cliente where rut = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1)))", nativeQuery = true)
    List<Detalle_Giro> listaTodo(long id);
    
    @Query(value = "select * from detalle_giro where rut_proveedor = ?1", nativeQuery = true)
    List<Detalle_Giro> cotizacionDetail(String rut);
}
package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Detalle_Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Detalle_Prestamo
public interface Detalle_PrestamoRepository extends JpaRepository<Detalle_Prestamo, Long>{
	
    @Query(value = "select * from detalle_prestamo where id_prestamo = ?1", nativeQuery = true)
    List<Detalle_Prestamo> DetailPrestamo(long id);
    
    @Query(value = "select * from detalle_prestamo where id_prestamo = (select id from prestamo where id = ?1 and devolucion = 1)", nativeQuery = true)
    List<Detalle_Prestamo> DetailByPrestados(long id);
    
    @Query(value = "select * from detalle_prestamo where id_prestamo = any "
            + "(select id from prestamo where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = any "
            + "(select rut from e_cliente where rut = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1))))", nativeQuery = true)
    List<Detalle_Prestamo> listaTodo(long id);
}
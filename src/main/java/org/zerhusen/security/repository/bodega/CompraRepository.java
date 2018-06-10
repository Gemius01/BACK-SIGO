package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Compra;
import org.zerhusen.model.bodega.Item;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Compra
public interface CompraRepository extends JpaRepository<Compra, Long>{

    //public void save(Item item);
    
    @Query(value = "select * from compra left join recepcion_compra on compra.id ="
            + " recepcion_compra.id_compra"
            + " where recepcion_compra.id_compra is null", nativeQuery = true)
    List<Compra> listaReporteComprasRecepcionPendiente();
    
    @Query(value = "select * from compra where num_factura = ?1", nativeQuery = true)
    Compra elNFactura(long num);
    
    @Query(value = "select * from compra where id_orden_compra = any "
            + "(select id from orden_compra where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = ?1)))", nativeQuery = true)
    List<Compra> listaTodo(long id);
}
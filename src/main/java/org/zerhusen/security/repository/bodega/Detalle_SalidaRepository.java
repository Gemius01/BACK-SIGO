package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Detalle_Salida;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Detalle_Salida
public interface Detalle_SalidaRepository extends JpaRepository<Detalle_Salida, Long> {

    @Query("SELECT id_item.nombre, COUNT(u) as thecount FROM Detalle_Salida u WHERE u.id_item.id<>0 group by id_item.id ORDER BY thecount DESC")
    List<Detalle_Salida> reporteMovimientos(Pageable pageable);

    @Query("SELECT id_salida.rutSolicitante, COUNT(u) as thecount  FROM Detalle_Salida u WHERE u.id_salida.rutSolicitante<>0 group by id_salida.rutSolicitante ORDER BY thecount DESC")
    List<Detalle_Salida> reporteMayorSolicitante(Pageable pageable);

    @Query(value = "select * from detalle_salida where id_salida = any "
            + "(select id from salida where id_bodega = any "
            + "(select id from bodega where id = 1 and id_e_cliente = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = 1?)))", nativeQuery = true)
    List<Detalle_Salida> listaTodo(long id);
}// id_item.id_subcategoria, id_item.nombre, id_item.marca, id_item.modelo, id_item.stock, id_item.id_unidad_medida,

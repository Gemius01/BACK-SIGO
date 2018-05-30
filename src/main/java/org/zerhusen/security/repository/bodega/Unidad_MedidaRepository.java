package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Unidad_Medida;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Unidad Medida
public interface Unidad_MedidaRepository extends JpaRepository<Unidad_Medida, Long>{
    
}
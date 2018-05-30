package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Tipo_Unidad;

//Interfaz de la base de datos con el controllador Tipo_Unidad
public interface Tipo_UnidadRepository extends JpaRepository<Tipo_Unidad, Long>{
	
}
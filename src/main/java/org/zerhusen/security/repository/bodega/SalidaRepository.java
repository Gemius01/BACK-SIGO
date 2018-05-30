package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Salida;

//Interfaz de la base de datos con el controllador Salida Items
public interface SalidaRepository extends JpaRepository<Salida, Long>{
	
}
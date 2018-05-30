package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Recepcion_Compra;

//Interfaz de la base de datos con el controllador Recepcion Compra
public interface Recepcion_CompraRepository extends JpaRepository<Recepcion_Compra, Long>{
	
}
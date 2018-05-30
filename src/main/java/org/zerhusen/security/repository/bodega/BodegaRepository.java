package org.zerhusen.security.repository.bodega;

import org.zerhusen.model.bodega.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

//Interfaz de la base de datos con el controllador Bodega
public interface BodegaRepository extends JpaRepository<Bodega, Long>{
	

}
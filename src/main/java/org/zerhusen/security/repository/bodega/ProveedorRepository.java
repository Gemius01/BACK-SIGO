package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Proveedor
public interface ProveedorRepository extends JpaRepository<Proveedor, String>{
	
    @Query(value = "select * from Proveedor where rut <> '11111111-1'", nativeQuery = true)
    List<Proveedor> listaTodo();
    
    @Query(value = "select * from Proveedor where nacional = ?1 and rut <> '11111111-1'", nativeQuery = true)
    List<Proveedor> listaNacional(String nacional);
    
}
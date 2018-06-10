package org.zerhusen.security.repository.bodega;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerhusen.model.bodega.Merma;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Interfaz de la base de datos con el controllador Merma
public interface MermaRepository extends JpaRepository<Merma, Long>{
	
    //devolverme todas las mermas con anulado en true
    @Query(value = "select * from merma where anulado = 1", nativeQuery = true)
    List<Merma> listaAnulados();
    
    @Query(value = "select * from merma where fecha BETWEEN ?1 and ?2 ", nativeQuery = true)
    List<Merma> mermSnAnulada (String fi, String ff);
    
    @Query(value = "select * from merma where id_bodega = "
            + "(select id from bodega where id = 1 and id_bodega = "
            + "(SELECT e_cliente.rut FROM user, e_cliente WHERE user.id = 1))", nativeQuery = true)
    List<Merma> listaTodo(long id);
}
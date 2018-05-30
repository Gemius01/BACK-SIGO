/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Merma;
import org.zerhusen.model.bodega.Orden_Compra;
import org.zerhusen.security.repository.bodega.Orden_CompraRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ferenc
 */
@RestController
public class Orden_CompraController {
    
    /*@PostConstruct
	public void init() throws ParseException{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                 String dateInString = "07/06/2013";
             
                Date date = formatter.parse(dateInString);
		repository.save(new Orden_Compra(date,"19415108-k"));
		
	}*/
    @Autowired
    private Orden_CompraRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/orden_compra/", method = GET)
    public Collection<Orden_Compra> getOrden_Compras() {
        return repository.listaTodo();
    }
    
    //Buscar a una Orden de Compra
    @CrossOrigin
    @RequestMapping(value = "/bodega/orden_compra/{id}", method = GET)
    public Optional<Orden_Compra> getOrden_Compra(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/orden_compra/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Orden_Compra nuevaOrden_Compra(@Valid @RequestBody Orden_Compra orden_compra) {
        repository.save(orden_compra);

        return orden_compra;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/orden_compra/{id}", method = PUT)
    public ResponseEntity<Optional<Orden_Compra>> actualizarOrden_Compra(@Valid @PathVariable long id, @RequestBody Orden_Compra actualizarOrden_Compra) {
        Optional<Orden_Compra> orden_compra = repository.findById(id);
        if (orden_compra != null) {

            actualizarOrden_Compra.setId(id);
            repository.save(actualizarOrden_Compra);
            return new ResponseEntity<>(orden_compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/orden_compra/{id}", method = DELETE)
    public ResponseEntity<Optional<Orden_Compra>> eliminarOrden_Compra(@PathVariable long id) {
        Optional<Orden_Compra> orden_compra = repository.findById(id);
        repository.deleteById(id);
        if (orden_compra != null) {
            return new ResponseEntity<>(orden_compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
     //Reporte de Ordenes pendientes
    @CrossOrigin
    @RequestMapping(value = "/bodega/orden_compra/ordenesPendientes", method = GET)
    public Collection<Orden_Compra> getOrdenesPendientes() {
        return repository.listaReporteOrdenesPendientes();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.zerhusen.model.bodega.Cotizacion;
import org.zerhusen.model.bodega.Recepcion_Compra;
import org.zerhusen.security.repository.bodega.Recepcion_CompraRepository;
import java.sql.Time;
import java.text.DateFormat;
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
public class Recepcion_CompraController {
   /*@PostConstruct
	public void init() throws ParseException{
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               String dateInString = "07/06/2013";
             
               Date date = formatter.parse(dateInString);
               String time = "15:30:18";

               java.sql.Time myTime = java.sql.Time.valueOf(time);
		repository.save(new Recepcion_Compra(date,myTime,"buena",3));
	}*/
        
    @Autowired
    private Recepcion_CompraRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/recepcion_compra/", method = GET)
    public Collection<Recepcion_Compra> getRecepcion_Compras() {
        return repository.findAll();
    }
    
    //Buscar a una Recepcion Compra
    @CrossOrigin
    @RequestMapping(value = "/bodega/recepcion_compra/{id}", method = GET)
    public Optional<Recepcion_Compra> getRecepcion_Compra(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar
    @CrossOrigin
    @RequestMapping(value = "/bodega/recepcion_compra/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Recepcion_Compra nuevaRecepcion_Compra(@Valid @RequestBody Recepcion_Compra recepcion_compra) {
        repository.save(recepcion_compra);

        return recepcion_compra;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/recepcion_compra/{id}", method = PUT)
    public ResponseEntity<Optional<Recepcion_Compra>> actualizarRecepcion_Compra(@Valid @PathVariable long id, @RequestBody Recepcion_Compra actualizarRecepcion_Compra) {
        Optional<Recepcion_Compra> recepcion_compra = repository.findById(id);
        if (recepcion_compra != null) {

            actualizarRecepcion_Compra.setId(id);
            repository.save(actualizarRecepcion_Compra);
            return new ResponseEntity<>(recepcion_compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/recepcion_compra/{id}", method = DELETE)
    public ResponseEntity<Optional<Recepcion_Compra>> eliminarRecepcion_Compra(@PathVariable long id) {
        Optional<Recepcion_Compra> recepcion_compra = repository.findById(id);
        repository.deleteById(id);
        if (recepcion_compra != null) {
            return new ResponseEntity<>(recepcion_compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
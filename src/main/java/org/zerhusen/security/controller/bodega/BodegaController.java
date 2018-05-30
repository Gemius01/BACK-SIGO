/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Bodega;
import org.zerhusen.security.repository.bodega.BodegaRepository;
import java.util.Collection;
import java.util.Optional;
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
public class BodegaController {
    
    @Autowired
    private BodegaRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/bodega/", method = GET)
    public Collection<Bodega> getBodegas() {
        return repository.findAll();
    }
    
    //Buscar a una Bodega
    @CrossOrigin
    @RequestMapping(value = "/bodega/bodega/{id}", method = GET)
    public Optional<Bodega> getBodega(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar Bodega)
    @CrossOrigin
    @RequestMapping(value = "/bodega/bodega/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Bodega nuevaBodega(@Valid @RequestBody Bodega bodega) {
        repository.save(bodega);
        return bodega;
    }
    
    // Petición PUT (Editar Bodega)
    @CrossOrigin
    @RequestMapping(value = "/bodega/bodega/{id}", method = PUT)
    public ResponseEntity<Optional<Bodega>> actualizarBodega(@Valid @PathVariable long id, @RequestBody Bodega actualizarBodega) {
        Optional<Bodega> bodega = repository.findById(id);
        if (bodega != null) {

            actualizarBodega.setId(id);
            repository.save(actualizarBodega);
            return new ResponseEntity<>(bodega, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar Bodega)
    @CrossOrigin
    @RequestMapping(value = "/bodega/bodega/{id}", method = DELETE)
    public ResponseEntity<Optional<Bodega>> eliminarBodega(@PathVariable long id) {
        Optional<Bodega> bodega = repository.findById(id);
        repository.deleteById(id);
        if (bodega != null) {
            return new ResponseEntity<>(bodega, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}

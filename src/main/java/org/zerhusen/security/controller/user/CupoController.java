/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Contrato;
import org.zerhusen.model.user.Cupo;
import org.zerhusen.security.repository.user.CupoRepository;
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
public class CupoController {
    
    @Autowired
    private CupoRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/cupo/", method = GET)
    public Collection<Cupo> getCupos() {
        return repository.findAll();
    }

    //Buscar a un cupo
    @CrossOrigin
    @RequestMapping(value = "/user/cupo/{id}", method = GET)
    public Optional<Cupo> getCupo(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/cupo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cupo nuevoCupo(@Valid @RequestBody Cupo cupo) {
        
        repository.save(cupo);

        return cupo;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/cupo/{id}", method = PUT)
    public ResponseEntity<Optional<Cupo>> actualizarCupo(@Valid @PathVariable long id, @RequestBody Cupo actualizarCupo) {
        Optional<Cupo> cupo = repository.findById(id);
        if (cupo != null) {

            actualizarCupo.setId(id);
            repository.save(actualizarCupo);
            return new ResponseEntity<>(cupo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/cupo/{id}", method = DELETE)
    public ResponseEntity<Optional<Cupo>> eliminarCupo(@PathVariable long id) {
        Optional<Cupo> cupo = repository.findById(id);
        repository.deleteById(id);
        if (cupo != null) {
            return new ResponseEntity<>(cupo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

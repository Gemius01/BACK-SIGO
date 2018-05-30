/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Seccion;
import org.zerhusen.security.repository.user.SeccionRepository;
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
public class SeccionController {
    
    @Autowired
    private SeccionRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/seccion/", method = GET)
    public Collection<Seccion> getSecciones() {
        return repository.findAll();
    }

    //Buscar a un pago
    @CrossOrigin
    @RequestMapping(value = "/user/seccion/{id}", method = GET)
    public Optional<Seccion> getSeccion(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/seccion/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Seccion nuevaSeccion(@Valid @RequestBody Seccion seccion) {
        
        repository.save(seccion);

        return seccion;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/seccion/{id}", method = PUT)
    public ResponseEntity<Optional<Seccion>> actualizarSeccion(@Valid @PathVariable long id, @RequestBody Seccion actualizarSeccion) {
        Optional<Seccion> seccion = repository.findById(id);
        if (seccion != null) {

            actualizarSeccion.setId(id);
            repository.save(actualizarSeccion);
            return new ResponseEntity<>(seccion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/seccion/{id}", method = DELETE)
    public ResponseEntity<Optional<Seccion>> eliminarSeccion(@PathVariable long id) {
        Optional<Seccion> seccion = repository.findById(id);
        repository.deleteById(id);
        if (seccion != null) {
            return new ResponseEntity<>(seccion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/user/seccion/{id}/modulo", method = GET)
    public Collection<Seccion> getSeccionByModulo(@PathVariable long id) {
        return repository.SeccionByModulo(id);
    }
}

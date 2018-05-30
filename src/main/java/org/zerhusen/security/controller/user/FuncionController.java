/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Funcion;
import org.zerhusen.security.repository.user.FuncionRepository;
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
public class FuncionController {
    
    @Autowired
    private FuncionRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/funcion/", method = GET)
    public Collection<Funcion> getFunciones() {
        return repository.findAll();
    }

    //Buscar a una funcion
    @CrossOrigin
    @RequestMapping(value = "/user/funcion/{id}", method = GET)
    public Optional<Funcion> getFuncion(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/funcion/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Funcion nuevaFuncion(@Valid @RequestBody Funcion funcion) {
        
        repository.save(funcion);

        return funcion;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/funcion/{id}", method = PUT)
    public ResponseEntity<Optional<Funcion>> actualizarFuncion(@Valid @PathVariable long id, @RequestBody Funcion actualizarFuncion) {
        Optional<Funcion> funcion = repository.findById(id);
        if (funcion != null) {

            actualizarFuncion.setId(id);
            repository.save(actualizarFuncion);
            return new ResponseEntity<>(funcion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/funcion/{id}", method = DELETE)
    public ResponseEntity<Optional<Funcion>> eliminarFuncion(@PathVariable long id) {
        Optional<Funcion> funcion = repository.findById(id);
        repository.deleteById(id);
        if (funcion != null) {
            return new ResponseEntity<>(funcion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/funcion/{id}/seccion", method = GET)
    public Collection<Funcion> getFuncionBySeccion(@PathVariable long id) {
        return repository.FuncionBySeccion(id);
    }
}

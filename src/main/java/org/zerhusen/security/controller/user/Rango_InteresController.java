/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Rango_Interes;
import org.zerhusen.security.repository.user.Rango_InteresRepository;
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
public class Rango_InteresController {
    
    @Autowired
    private Rango_InteresRepository repository;
    
    //Peticion GET (Mostar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/rango_interes/", method = GET)
    public Collection<Rango_Interes> getRangos_Interes() {
        return repository.findAll();
    }
    
    //Buscar a un Anexo
    @CrossOrigin
    @RequestMapping(value = "/user/rango_interes/{id}", method = GET)
    public Optional<Rango_Interes> getRango_Interes(@PathVariable long id){
        return repository.findById(id);
    }
    
    //Peticion POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/rango_interes/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Rango_Interes nuevoRango_Interes(@Valid @RequestBody Rango_Interes rango_interes){
        
        repository.save(rango_interes);
        
        return rango_interes;
    }
    
    //Peticion PUT(Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/rango_interes/{id}", method = PUT)
    public ResponseEntity<Optional<Rango_Interes>> actualizarRango_Interes(@Valid @PathVariable long id, @RequestBody Rango_Interes actualizarRango_Interes) {
        Optional<Rango_Interes> rango_interes = repository.findById(id);
        if (rango_interes != null) {

            actualizarRango_Interes.setId(id);
            repository.save(actualizarRango_Interes);
            
            return new ResponseEntity<>(rango_interes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/rango_interes{id}", method = DELETE)
    public ResponseEntity<Optional<Rango_Interes>> eliminarRango_Interes(@PathVariable long id) {
        Optional<Rango_Interes> rango_interes = repository.findById(id);
        repository.deleteById(id);
        if (rango_interes != null) {
            return new ResponseEntity<>(rango_interes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

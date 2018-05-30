/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Modulo;
import org.zerhusen.security.repository.user.ModuloRepository;
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
public class ModuloController {
    
    @Autowired
    private ModuloRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/modulo/", method = GET)
    public Collection<Modulo> getModulos() {
        return repository.findAll();
    }

    //Buscar a un modulo
    @CrossOrigin
    @RequestMapping(value = "/user/modulo/{id}", method = GET)
    public Optional<Modulo> getModulo(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/modulo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Modulo nuevoModulo(@Valid @RequestBody Modulo modulo) {
        
        repository.save(modulo);

        return modulo;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/modulo/{id}", method = PUT)
    public ResponseEntity<Optional<Modulo>> actualizarModulo(@Valid @PathVariable long id, @RequestBody Modulo actualizarModulo) {
        Optional<Modulo> modulo = repository.findById(id);
        if (modulo != null) {

            actualizarModulo.setId(id);
            repository.save(actualizarModulo);
            return new ResponseEntity<>(modulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/modulo/{id}", method = DELETE)
    public ResponseEntity<Optional<Modulo>> eliminarModulo(@PathVariable long id) {
        Optional<Modulo> modulo = repository.findById(id);
        repository.deleteById(id);
        if (modulo != null) {
            return new ResponseEntity<>(modulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

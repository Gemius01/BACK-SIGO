/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Servicio;
import org.zerhusen.security.repository.user.ServicioRepository;
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
public class ServicioController {
    
    @Autowired
    private ServicioRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/servicio/", method = GET)
    public Collection<Servicio> getServicios() {
        return repository.findAll();
    }

    //Buscar a un servicio
    @CrossOrigin
    @RequestMapping(value = "/user/servicio/{id}", method = GET)
    public Optional<Servicio> getServicio(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/servicio/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Servicio nuevoServicio(@Valid @RequestBody Servicio servicio) {
        
        repository.save(servicio);

        return servicio;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/servicio/{id}", method = PUT)
    public ResponseEntity<Optional<Servicio>> actualizarServicio(@Valid @PathVariable long id, @RequestBody Servicio actualizarServicio) {
        Optional<Servicio> servicio = repository.findById(id);
        if (servicio != null) {

            actualizarServicio.setId(id);
            repository.save(actualizarServicio);
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/servicio/{id}", method = DELETE)
    public ResponseEntity<Optional<Servicio>> eliminarServicio(@PathVariable long id) {
        Optional<Servicio> servicio = repository.findById(id);
        repository.deleteById(id);
        if (servicio != null) {
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

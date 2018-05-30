/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.D_Servicio;
import org.zerhusen.model.user.Modulo;
import org.zerhusen.security.repository.user.D_ServicioRepository;
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
public class D_ServicioController {
    
    @Autowired
    private D_ServicioRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/d_servicio/", method = GET)
    public Collection<D_Servicio> getD_Servicios() {
        return repository.findAll();
    }

    //Buscar a un d_servicio
    @CrossOrigin
    @RequestMapping(value = "/user/d_servicio/{id}", method = GET)
    public Optional<D_Servicio> getD_Servicio(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/d_servicio/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public D_Servicio nuevoD_Servicio(@Valid @RequestBody D_Servicio d_servicio) {
        
        repository.save(d_servicio);

        return d_servicio;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/d_servicio/{id}", method = PUT)
    public ResponseEntity<Optional<D_Servicio>> actualizarD_Servicio(@Valid @PathVariable long id, @RequestBody D_Servicio actualizarD_Servicio) {
        Optional<D_Servicio> d_servicio = repository.findById(id);
        if (d_servicio != null) {

            actualizarD_Servicio.setId(id);
            repository.save(actualizarD_Servicio);
            return new ResponseEntity<>(d_servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/d_servicio/{id}", method = DELETE)
    public ResponseEntity<Optional<D_Servicio>> eliminarD_Servicio(@PathVariable long id) {
        Optional<D_Servicio> d_servicio = repository.findById(id);
        repository.deleteById(id);
        if (d_servicio != null) {
            return new ResponseEntity<>(d_servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/d_servicio/{id}/servicio", method = GET)
    public Collection<D_Servicio> getDetailByServicio(@PathVariable long id) {
        return repository.DetailByServicio(id);
    }
}

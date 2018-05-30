/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Pago;
import org.zerhusen.security.repository.user.PagoRepository;
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
public class PagoController {
    
    @Autowired
    private PagoRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/pago/", method = GET)
    public Collection<Pago> getPagos() {
        return repository.findAll();
    }

    //Buscar a un pago
    @CrossOrigin
    @RequestMapping(value = "/user/pago/{id}", method = GET)
    public Optional<Pago> getPago(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/pago/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pago nuevoPago(@Valid @RequestBody Pago pago) {
        
        repository.save(pago);

        return pago;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/pago/{id}", method = PUT)
    public ResponseEntity<Optional<Pago>> actualizarPago(@Valid @PathVariable long id, @RequestBody Pago actualizarPago) {
        Optional<Pago> pago = repository.findById(id);
        if (pago != null) {

            actualizarPago.setId(id);
            repository.save(actualizarPago);
            return new ResponseEntity<>(pago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/pago/{id}", method = DELETE)
    public ResponseEntity<Optional<Pago>> eliminarPago(@PathVariable long id) {
        Optional<Pago> pago = repository.findById(id);
        repository.deleteById(id);
        if (pago != null) {
            return new ResponseEntity<>(pago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

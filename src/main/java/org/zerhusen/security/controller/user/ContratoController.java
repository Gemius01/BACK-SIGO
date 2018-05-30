/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Contrato;
import org.zerhusen.security.repository.user.ContratoRepository;
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
public class ContratoController {
    
    @Autowired
    private ContratoRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/", method = GET)
    public Collection<Contrato> getContratos() {
        return repository.findAll();
    }

    //Buscar a un contrato
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{id}", method = GET)
    public Optional<Contrato> getContrato(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Contrato nuevoContrato(@Valid @RequestBody Contrato contrato) {
        
        repository.save(contrato);

        return contrato;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{id}", method = PUT)
    public ResponseEntity<Optional<Contrato>> actualizarContrato(@Valid @PathVariable long id, @RequestBody Contrato actualizarContrato) {
        Optional<Contrato> contrato = repository.findById(id);
        if (contrato != null) {

            actualizarContrato.setId(id);
            repository.save(actualizarContrato);
            return new ResponseEntity<>(contrato, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{id}", method = DELETE)
    public ResponseEntity<Optional<Contrato>> eliminarContrato(@PathVariable long id) {
        Optional<Contrato> contrato = repository.findById(id);
        repository.deleteById(id);
        if (contrato != null) {
            return new ResponseEntity<>(contrato, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

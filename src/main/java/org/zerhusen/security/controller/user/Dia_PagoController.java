/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Dia_Pago;
import org.zerhusen.security.repository.user.Dia_PagoRepository;
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
public class Dia_PagoController {
    
    @Autowired
    private Dia_PagoRepository repository;
    
    //Peticion GET (Mostar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/dia_pago/", method = GET)
    public Collection<Dia_Pago> getDia_Pagos() {
        return repository.findAll();
    }
    
    //Buscar a un Anexo
    @CrossOrigin
    @RequestMapping(value = "/user/dia_pago/{id}", method = GET)
    public Optional<Dia_Pago> getDia_Pago(@PathVariable long id){
        return repository.findById(id);
    }
    
    //Peticion POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/dia_pago/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Dia_Pago nuevoDia_Pago(@Valid @RequestBody Dia_Pago dia_pago){
        
        repository.save(dia_pago);
        
        return dia_pago;
    }
    
    //Peticion PUT(Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/dia_pago/{id}", method = PUT)
    public ResponseEntity<Optional<Dia_Pago>> actualizarDia_Pago(@Valid @PathVariable long id, @RequestBody Dia_Pago actualizarDia_Pago) {
        Optional<Dia_Pago> dia_pago = repository.findById(id);
        if (dia_pago != null) {

            actualizarDia_Pago.setId(id);
            repository.save(actualizarDia_Pago);
            
            return new ResponseEntity<>(dia_pago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/dia_pago/{id}", method = DELETE)
    public ResponseEntity< Optional<Dia_Pago>> eliminarDia_Pago(@PathVariable long id) {
        Optional<Dia_Pago> dia_pago = repository.findById(id);
        repository.deleteById(id);
        if (dia_pago != null) {
            return new ResponseEntity<>(dia_pago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

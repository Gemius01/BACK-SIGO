/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Conf_E_Cliente;
import org.zerhusen.security.repository.user.Conf_E_ClienteRepository;
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
public class Conf_E_ClienteController {
    
    @Autowired
    private Conf_E_ClienteRepository repository;
    
    //Peticion GET (Mostar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/conf_e_cliente/", method = GET)
    public Collection<Conf_E_Cliente> getConf_E_Clientes() {
        return repository.findAll();
    }
    
    //Buscar a una configuracion
    @CrossOrigin
    @RequestMapping(value = "/user/conf_e_cliente/{id}", method = GET)
    public Optional<Conf_E_Cliente> getConf_E_Cliente(@PathVariable long id){
        return repository.findById(id);
    }
    
    //Peticion POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/conf_e_cliente/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Conf_E_Cliente nuevoConf_E_Cliente(@Valid @RequestBody Conf_E_Cliente conf_E_Cliente){
        
        repository.save(conf_E_Cliente);
        
        return conf_E_Cliente;
    }
    
    //Peticion PUT(Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/conf_e_cliente/{id}", method = PUT)
    public ResponseEntity<Optional<Conf_E_Cliente>> actualizarConf_E_Cliente(@Valid @PathVariable long id, @RequestBody Conf_E_Cliente actualizarConf_E_Cliente) {
        Optional<Conf_E_Cliente> conf_e_cliente = repository.findById(id);
        if (conf_e_cliente != null) {

            actualizarConf_E_Cliente.setId(id);
            repository.save(actualizarConf_E_Cliente);
            
            return new ResponseEntity<>(conf_e_cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
     @CrossOrigin
    @RequestMapping(value = "/user/conf_e_cliente/{id}", method = DELETE)
    public ResponseEntity<Optional<Conf_E_Cliente>> eliminarConf_E_Cliente(@PathVariable long id) {
        Optional<Conf_E_Cliente> conf_e_cliente = repository.findById(id);
        repository.deleteById(id);
        if (conf_e_cliente != null) {
            return new ResponseEntity<>(conf_e_cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

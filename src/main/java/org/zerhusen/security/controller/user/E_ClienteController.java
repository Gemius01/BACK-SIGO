/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import com.google.gson.JsonObject;
import org.zerhusen.model.user.E_Cliente;
import org.zerhusen.model.security.ValiRut;
import org.zerhusen.security.repository.user.E_ClienteRepository;
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
public class E_ClienteController {
    
    @Autowired
    private E_ClienteRepository repository;
    
     ValiRut valirut = new ValiRut();
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/e_cliente/", method = GET)
    public Collection<E_Cliente> getE_Clientes() {
        return repository.findAll();
    }

    //Buscar a un e_cliente
    @CrossOrigin
    @RequestMapping(value = "/user/e_cliente/{id}", method = GET)
    public Optional<E_Cliente> getE_Cliente(@PathVariable String rut) {
        return repository.findById(rut);
    }
    
    // Petición POST(Agregar)   
    @CrossOrigin
    @RequestMapping(value = "/user/e_cliente/", method = POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String nuevoE_Cliente(@Valid @RequestBody E_Cliente e_cliente) {
        
        JsonObject in_ext = new JsonObject();
        JsonObject object = new JsonObject();
        JsonObject in_rut = new JsonObject();
        
         if (repository.findById(e_cliente.getRut()) != null) {
            in_ext.addProperty("defaultMessage", "Este Rut ya Existe");
            String json_ext = in_ext.toString();
            return json_ext;
        } else if (valirut.validarRut(e_cliente.getRut())) {
            repository.save(e_cliente);
            object.addProperty("rut", e_cliente.getRut());
            String json_object2 = object.toString();
            return json_object2;
        } else {
            in_rut.addProperty("defaultMessage", "Rut Invalido");
            String json_rut = in_rut.toString();
            return json_rut;
        }
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/e_cliente/{id}", method = PUT)
    public ResponseEntity<Optional<E_Cliente>> actualizarE_Cliente(@Valid @PathVariable String rut, @RequestBody E_Cliente actualizarE_Cliente) {
        Optional<E_Cliente> e_cliente = repository.findById(rut);
        if (e_cliente != null) {

            actualizarE_Cliente.setRut(rut);
            repository.save(actualizarE_Cliente);
            return new ResponseEntity<>(e_cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/e_cliente/{id}", method = DELETE)
    public ResponseEntity<Optional<E_Cliente>> eliminarE_Cliente(@PathVariable String rut) {
        Optional<E_Cliente> e_cliente = repository.findById(rut);
        repository.deleteById(rut);
        if (e_cliente != null) {
            return new ResponseEntity<>(e_cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

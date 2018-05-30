/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.D_Funciones;
import org.zerhusen.security.repository.user.D_FuncionesRepository;
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
public class D_FuncionesController {
    
    //crud incompleto solo leer datos y guardar
    
    @Autowired
    private D_FuncionesRepository repository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/d_funciones/", method = GET)
    public Collection<D_Funciones> getD_Funciones() {
        return repository.findAll();
    }

    //Buscar a un detalle de funcion
    @CrossOrigin
    @RequestMapping(value = "/user/d_funciones/{id}", method = GET)
    public Optional<D_Funciones> getD_Funcion(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/d_funciones/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public D_Funciones nuevaD_Funcion(@Valid @RequestBody D_Funciones d_funciones) {
        
        repository.save(d_funciones);

        return d_funciones;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/d_funcion/{id}", method = PUT)
    public ResponseEntity<Optional<D_Funciones>> actualizarD_Funciones(@Valid @PathVariable long id, @RequestBody D_Funciones actualizarD_Funciones) {
        Optional<D_Funciones> d_funciones = repository.findById(id);
        if (d_funciones != null) {

            actualizarD_Funciones.setId(id);
            repository.save(actualizarD_Funciones);
            return new ResponseEntity<>(d_funciones, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/d_funcion/{id}", method = DELETE)
    public ResponseEntity<Optional<D_Funciones>> eliminarD_Funciones(@PathVariable long id) {
        Optional<D_Funciones> d_funciones = repository.findById(id);
        repository.deleteById(id);
        if (d_funciones != null) {
            return new ResponseEntity<>(d_funciones, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

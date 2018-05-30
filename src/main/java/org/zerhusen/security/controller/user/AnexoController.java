/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Anexo;
import org.zerhusen.security.repository.user.AnexoRepository;
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
public class AnexoController {
    
    @Autowired
    private AnexoRepository repository;
    
    //Peticion GET (Mostar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/anexo/", method = GET)
    public Collection<Anexo> getAnexos() {
        return repository.findAll();
    }
    
    //Buscar a un Anexo
    @CrossOrigin
    @RequestMapping(value = "/user/anexo/{id}", method = GET)
    public Optional<Anexo> getAnexo(@PathVariable long id){
        return repository.findById(id);
    }
    
    //Peticion POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/anexo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Anexo nuevoAnexo(@Valid @RequestBody Anexo anexo){
        
        repository.save(anexo);
        
        return anexo;
    }
    
    //Peticion PUT(Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/anexo/{id}", method = PUT)
    public ResponseEntity< Optional<Anexo>> actualizarAnexo(@Valid @PathVariable long id, @RequestBody Anexo actualizarAnexo) {
        Optional<Anexo> anexo = repository.findById(id);
        if (anexo != null) {

            actualizarAnexo.setId(id);
            repository.save(actualizarAnexo);
            
            return new ResponseEntity<>(anexo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/anexo/{id}", method = DELETE)
    public ResponseEntity<Optional<Anexo>> eliminarAnexo(@PathVariable long id) {
        Optional<Anexo> anexo = repository.findById(id);
        repository.deleteById(id);
        if (anexo != null) {
            return new ResponseEntity<>(anexo,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Funcionario;
import org.zerhusen.security.repository.user.FuncionarioRepository;
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
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository repository;
    
    //Peticion GET (Mostar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/funcionario/", method = GET)
    public Collection<Funcionario> getFuncionarios() {
        return repository.findAll();
    }
    
    //Buscar a un Funcionario
    @CrossOrigin
    @RequestMapping(value = "/user/funcionario/{rut}", method = GET)
    public Optional<Funcionario> getFuncionario(@PathVariable String rut){
        return repository.findById(rut);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/funcionario/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario nuevoFuncionario(@Valid @RequestBody Funcionario funcionario) {
        
        repository.save(funcionario);

        return funcionario;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/funcionario/{rut}", method = PUT)
    public ResponseEntity<Optional<Funcionario>> actualizarProveedor(@Valid @PathVariable String rut, @RequestBody Funcionario actualizarFuncionario) {
        Optional<Funcionario> funcionario = repository.findById(rut);
        if (funcionario != null) {
            actualizarFuncionario.setRut(rut);
            repository.save(actualizarFuncionario);
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/funcionario/{rut}", method = DELETE)
    public ResponseEntity<Optional<Funcionario>> eliminarFuncionario(@PathVariable String rut) {
        Optional<Funcionario> funcionario = repository.findById(rut);
        repository.deleteById(rut);
        if (funcionario != null) {
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

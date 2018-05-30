/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import org.zerhusen.model.user.Usuario;
import org.zerhusen.security.repository.user.UsuarioRepository;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
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
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;


    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/usuario/", method = GET)
    public Collection<Usuario> getUsuarios() {
        return repository.findAll();
    }

    //Buscar a un usuario
    @CrossOrigin
    @RequestMapping(value = "/user/usuario/{id}", method = GET)
    public Optional<Usuario> getUsuario(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/usuario/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario nuevoUsuario(@Valid @RequestBody Usuario usuario) {

        repository.save(usuario);

        return usuario;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/usuario/{id}", method = PUT)
    public ResponseEntity<Optional<Usuario>> actualizarUsuario(@Valid @PathVariable long id, @RequestBody Usuario actualizarUsuario) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario != null) {

            actualizarUsuario.setId(id);
            repository.save(actualizarUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/usuario/{id}", method = DELETE)
    public ResponseEntity<Optional<Usuario>> eliminarUsuario(@PathVariable long id) {
        Optional<Usuario> usuario = repository.findById(id);
        repository.deleteById(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

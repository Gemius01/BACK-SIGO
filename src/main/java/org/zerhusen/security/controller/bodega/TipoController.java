/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Tipo;
import org.zerhusen.security.repository.bodega.TipoRepository;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Ferenc
 */
@RestController
public class TipoController {

    @PostConstruct
    public void init() throws ParseException {
        if (repository.findAll().isEmpty() == true) {
            repository.save(new Tipo("Herramienta"));
            repository.save(new Tipo("Insumo"));
        }
    }
    
    @Autowired
    private TipoRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/bodega/tipo/", method = GET)
    public Collection<Tipo> getTipos() {
        return repository.listaTodo();
    }

    //Buscar a un Tipo
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo/{id}", method = GET)
    public Optional<Tipo> getTipo(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Tipo nuevoTipo(@Valid @RequestBody Tipo tipo) {
        repository.save(tipo);
        return tipo;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo/{id}", method = PUT)
    public ResponseEntity<Optional<Tipo>> actualizarTipo(@Valid @PathVariable long id, @RequestBody Tipo actualizarTipo) {
        Optional<Tipo> tipo = repository.findById(id);
        if (tipo != null) {
            actualizarTipo.setId(id);
            repository.save(actualizarTipo);
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo/{id}", method = DELETE)
    public ResponseEntity<Optional<Tipo>> eliminarTipo(@PathVariable long id) {
        Optional<Tipo> tipo = repository.findById(id);
        repository.deleteById(id);
        if (tipo != null) {
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

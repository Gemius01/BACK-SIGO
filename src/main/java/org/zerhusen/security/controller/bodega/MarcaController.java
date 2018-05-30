/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Categoria;
import org.zerhusen.model.bodega.Marca;
import org.zerhusen.security.repository.bodega.CategoriaRepository;
import org.zerhusen.security.repository.bodega.MarcaRepository;
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
public class MarcaController {
    
    @Autowired
    private MarcaRepository repository;
 
     // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/marca/", method = GET)
    public Collection<Marca> getMarcas() {
        return repository.listaTodo();
    }

    //Buscar a una Marca
    @CrossOrigin
    @RequestMapping(value = "/bodega/marca/{id}", method = GET)
    public Optional<Marca> getMarca(@PathVariable long id) {
        return repository.findById(id);
    }
    
    
    // Petición POST(Agregar Marca)
    @CrossOrigin
    @RequestMapping(value = "/bodega/marca/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Marca nuevaMarca(@Valid @RequestBody Marca marca) {
        repository.save(marca);
        return marca;
    }
    
    // Petición PUT (Editar Marca)
    @CrossOrigin
    @RequestMapping(value = "/bodega/marca/{id}", method = PUT)
    public ResponseEntity<Optional<Marca>> actualizarMarca(@Valid @PathVariable long id, @RequestBody Marca actualizarMarca) {
        Optional<Marca> marca = repository.findById(id);
        if (marca != null) {

            actualizarMarca.setId(id);
            repository.save(actualizarMarca);
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar Marca)
    @CrossOrigin
    @RequestMapping(value = "/bodega/marca/{id}", method = DELETE)
    public ResponseEntity<Optional<Marca>> eliminarMarca(@PathVariable long id) {
        Optional<Marca> marca = repository.findById(id);
        repository.deleteById(id);
        if (marca != null) {
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

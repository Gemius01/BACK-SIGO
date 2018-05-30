/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Tipo_Unidad;
import org.zerhusen.security.repository.bodega.Tipo_UnidadRepository;
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
public class Tipo_UnidadController {
       
    /*@PostConstruct
	public void init() throws ParseException{
		repository.save(new Tipo("Cualquier Tipo"));
    }*/
    @Autowired
    private Tipo_UnidadRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo_unidad/", method = GET)
    public Collection<Tipo_Unidad> getTipos_unidades() {
        return repository.findAll();
    }
    
    //Buscar a un Tipo_unidad
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo_unidad/{id}", method = GET)
    public Optional<Tipo_Unidad> getTipo_Unidad(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo_unidad/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Tipo_Unidad nuevoTipo_Unidad(@Valid @RequestBody Tipo_Unidad tipo_unidad) {
        repository.save(tipo_unidad);
        return tipo_unidad;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo_unidad/{id}", method = PUT)
    public ResponseEntity<Optional<Tipo_Unidad>> actualizarTipo_Unidad(@Valid @PathVariable long id, @RequestBody Tipo_Unidad actualizarTipo_unidad) {
        Optional<Tipo_Unidad> tipo_unidad = repository.findById(id);
        if (tipo_unidad != null) {
            actualizarTipo_unidad.setId(id);
            repository.save(actualizarTipo_unidad);
            return new ResponseEntity<>(tipo_unidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/tipo_unidad/{id}", method = DELETE)
    public ResponseEntity<Optional<Tipo_Unidad>> eliminarTipo_Unidad(@PathVariable long id) {
        Optional<Tipo_Unidad> tipo_unidad = repository.findById(id);
        repository.deleteById(id);
        if (tipo_unidad != null) {
            return new ResponseEntity<>(tipo_unidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


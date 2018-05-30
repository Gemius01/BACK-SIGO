/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Unidad_Medida;
import org.zerhusen.security.repository.bodega.Unidad_MedidaRepository;
import org.zerhusen.model.bodega.Tipo_Unidad;
import org.zerhusen.security.repository.bodega.Tipo_UnidadRepository;
import java.text.ParseException;
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
public class Unidad_MedidaController {
    
    @Autowired
    private Unidad_MedidaRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/unidad_medida/", method = GET)
    public Collection<Unidad_Medida> getUnidad_Medidas() {
        return repository.findAll();
    }
    
    //Buscar a una Unidad Medida
    @CrossOrigin
    @RequestMapping(value = "/bodega/unidad_medida/{id}", method = GET)
    public Optional<Unidad_Medida> getUnidad_Medida(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/unidad_medida/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Unidad_Medida nuevaUnidad_Medida(@Valid @RequestBody Unidad_Medida unidad_medida) {
        repository.save(unidad_medida);

        return unidad_medida;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/unidad_medida/{id}", method = PUT)
    public ResponseEntity<Optional<Unidad_Medida>> actualizarUnidad_Medida(@Valid @PathVariable long id, @RequestBody Unidad_Medida actualizarUnidad_Medida) {
        Optional<Unidad_Medida> unidad_medida = repository.findById(id);
        if (unidad_medida != null) {

            actualizarUnidad_Medida.setId(id);
            repository.save(actualizarUnidad_Medida);
            return new ResponseEntity<>(unidad_medida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/unidad_medida/{id}", method = DELETE)
    public ResponseEntity<Optional<Unidad_Medida>> eliminarUnidad_Medida(@PathVariable long id) {
        Optional<Unidad_Medida> unidad_medida = repository.findById(id);
        repository.deleteById(id);
        if (unidad_medida != null) {
            return new ResponseEntity<>(unidad_medida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

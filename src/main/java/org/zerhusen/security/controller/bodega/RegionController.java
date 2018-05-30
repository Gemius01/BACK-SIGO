/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Region;
import org.zerhusen.model.bodega.Salida;
import org.zerhusen.security.repository.bodega.RegionRepository;
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
public class RegionController {
    
    @Autowired
    private RegionRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/region/", method = GET)
    public Collection<Region> getRegiones() {
        return repository.listaTodo();
    }
    
    //Buscar a una Region
    @CrossOrigin
    @RequestMapping(value = "/bodega/region/{id}", method = GET)
    public Optional<Region> getRegion(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/region/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Region nuevaRegion(@Valid @RequestBody Region region) {
        
        repository.save(region);

        return region;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/region/{id}", method = PUT)
    public ResponseEntity<Optional<Region>> actualizarRegion(@Valid @PathVariable long id, @RequestBody Region actualizarRegion) {
        Optional<Region> region = repository.findById(id);
        if (region != null) {

            actualizarRegion.setId(id);
            repository.save(actualizarRegion);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/region/{id}", method = DELETE)
    public ResponseEntity<Optional<Region>> eliminarSalida(@PathVariable long id) {
        Optional<Region> region = repository.findById(id);
        repository.deleteById(id);
        if (region != null) {
            return new ResponseEntity<>(region, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.zerhusen.model.bodega.Pais;
import org.zerhusen.security.repository.bodega.PaisRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import static org.hibernate.criterion.Projections.property;
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
public class PaisController {

    @Autowired
    private PaisRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/pais/", method = GET)
    public Collection<Pais> getPaises() {
        return repository.listaTodo();
    }

    //Buscar a una Region
    @CrossOrigin
    @RequestMapping(value = "/bodega/pais/{id}", method = GET)
    public Optional<Pais> getPais(@PathVariable long id) {
        return repository.findById(id);
    }
    
//    @CrossOrigin
//    @RequestMapping(value = "/bodega/elpais/", method = GET, produces = "application/json")
//    public String pruebaPais(){
//
//        Gson gson = new Gson();
//        Pais pais = new Pais();
//        
//        repository.listaTodo();
//        
//        JsonArray array = new JsonArray();
//        array.add("");
//        array.add("xao");
//        
//        String lista = array.toString();
//
//        JsonObject object = new JsonObject();
//        object.addProperty("name", "Juan");
//        object.addProperty("birthday", "10-10-1996");
//        object.addProperty("age", 15);
//        object.addProperty("chileno", Boolean.FALSE);
//
//        String json = object.toString();
//        
//        return array.toString();
//    }
//    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/pais/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pais nuevoPais(@Valid @RequestBody Pais pais) {

        repository.save(pais);

        return pais;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/pais/{id}", method = PUT)
    public ResponseEntity<Optional<Pais>> actualizarPais(@Valid @PathVariable long id, @RequestBody Pais actualizarPais) {
        Optional<Pais> pais = repository.findById(id);
        if (pais != null) {

            actualizarPais.setId(id);
            repository.save(actualizarPais);
            return new ResponseEntity<>(pais, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/pais/{id}", method = DELETE)
    public ResponseEntity<Optional<Pais>> eliminarPais(@PathVariable long id) {
        Optional<Pais> pais = repository.findById(id);
        repository.deleteById(id);
        if (pais != null) {
            return new ResponseEntity<>(pais, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.google.gson.JsonObject;
import org.zerhusen.model.bodega.Salida;
import org.zerhusen.model.security.ValiRut;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.security.repository.bodega.SalidaRepository;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Ferenc
 */
@RestController
public class SalidaController {
    /*@PostConstruct
	public void init() throws ParseException{
              SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
              String dateInString = "07/06/2013";
             
              Date date = formatter.parse(dateInString);
                
                
                String time = "15:30:18";
                java.sql.Time myTime = java.sql.Time.valueOf(time);
                
		repository.save(new Salida(date,myTime,"19415108-k","Rotos","Juan Perico"));
	}*/
    @Autowired
    private SalidaRepository repository;
    
    ValiRut valirut = new ValiRut();

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/salida/", method = GET)
    public Collection<Salida> getSalidas() {
        return repository.findAll();
    }
    
    //Buscar a una Salida
    @CrossOrigin
    @RequestMapping(value = "/bodega/salida/{id}", method = GET)
    public Optional<Salida> getSalida(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/salida/", method = POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String nuevaSalida(@Valid @RequestBody Salida salida) {
        
        JsonObject in_rut = new JsonObject();
        JsonObject object = new JsonObject();
        
        if (valirut.validarRut(salida.getRutSolicitante())) {
            repository.save(salida);
            object.addProperty("id", salida.getId());
            String json_object = object.toString();
            return json_object;
        } else {
            in_rut.addProperty("defaultMessage", "Rut Invalido");
            String json_rut = in_rut.toString();
            return json_rut;
        }
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/salida/{id}", method = PUT)
    public ResponseEntity<Optional<Salida>> actualizarSalida(@Valid @PathVariable long id, @RequestBody Salida actualizarSalida) {
        Optional<Salida> salida_items = repository.findById(id);
        if (salida_items != null) {

            actualizarSalida.setId(id);
            repository.save(actualizarSalida);
            return new ResponseEntity<>(salida_items, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/salida/{id}", method = DELETE)
    public ResponseEntity<Optional<Salida>> eliminarSalida(@PathVariable long id) {
        Optional<Salida> salida = repository.findById(id);
        repository.deleteById(id);
        if (salida != null) {
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

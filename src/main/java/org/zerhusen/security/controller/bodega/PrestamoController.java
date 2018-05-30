/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.zerhusen.model.bodega.Detalle_Prestamo;
import org.zerhusen.model.bodega.Prestamo;
import org.zerhusen.model.security.ValiRut;
import org.zerhusen.security.repository.bodega.PrestamoRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
public class PrestamoController {

    ValiRut valirut = new ValiRut();

    /*@PostConstruct
	public void init() throws ParseException{
                 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                 String dateInString = "07/06/2013";
             
                Date date = formatter.parse(dateInString);
                
               
                String time = "15:30:18";
                java.sql.Time myTime = java.sql.Time.valueOf(time);
                
		repository.save(new Prestamo(date,myTime,"19415108-k","sin plata",true,date,"Juan Perico"));
		
	}*/
    @Autowired
    private PrestamoRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/prestamo/", method = GET)
    public Collection<Prestamo> getPrestamos() {
        return repository.findAll();
    }

    //Buscar a un Prestamo
    @CrossOrigin
    @RequestMapping(value = "/bodega/prestamo/{id}", method = GET)
    public Optional<Prestamo> getPrestamo(@PathVariable long id) {
        return repository.findById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/bodega/prestamo/valueFalse", method = GET)
    public Collection<Prestamo> getPrestamosByValueFalse() {
//       Collection<Prestamo> prestamos = repository.findAll();
//       ArrayList itemsDetail = new ArrayList<>();
//       
//       for (Prestamo c: prestamos) {
//           if (c.isDevolucion() == false ) {
//               itemsDetail.add(c);
//           }
//       }
//          
//        return itemsDetail;
        return repository.devolucionBool(0);
    }

    @CrossOrigin
    @RequestMapping(value = "/bodega/prestamo/valueTrue", method = GET)
    public Collection<Prestamo> getPrestamosByValueTrue() {
//        Collection<Prestamo> prestamos = repository.findAll();
//        ArrayList itemsDetail = new ArrayList<>();
//        
//        for (Prestamo c : prestamos) {
//            if (c.isDevolucion() == true) {
//                itemsDetail.add(c);
//            }
//        }
//        
//        return itemsDetail;
        return repository.devolucionBool(1);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/prestamo/", method = POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String nuevoPrestamo(@Valid @RequestBody Prestamo prestamo) {

        JsonObject in_rut = new JsonObject();
        JsonObject object = new JsonObject();

        if (valirut.validarRut(prestamo.getRutSolicitante())) {
            repository.save(prestamo);
            object.addProperty("id", prestamo.getId());
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
    @RequestMapping(value = "/bodega/prestamo/{id}", method = PUT)
    public ResponseEntity<Optional<Prestamo>> actualizarPrestamo(@Valid @PathVariable long id, @RequestBody Prestamo actualizarPrestamo) {
        Optional<Prestamo> prestamo = repository.findById(id);
        if (prestamo != null) {
            actualizarPrestamo.setId(id);
            repository.save(actualizarPrestamo);
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/prestamo/{id}", method = DELETE)
    public ResponseEntity<Optional<Prestamo>> eliminarPrestamo(@PathVariable long id) {
        Optional<Prestamo> prestamo = repository.findById(id);
        repository.deleteById(id);
        if (prestamo != null) {
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

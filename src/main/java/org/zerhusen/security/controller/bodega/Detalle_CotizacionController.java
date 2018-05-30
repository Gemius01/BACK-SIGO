/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Detalle_Cotizacion;
import org.zerhusen.security.repository.bodega.Detalle_CotizacionRepository;
import java.util.ArrayList;
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
public class Detalle_CotizacionController {

    @Autowired
    private Detalle_CotizacionRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_cotizacion/", method = GET)
    public Collection<Detalle_Cotizacion> getDetalles_Cotizacion() {
        return repository.findAll();
    }

    //Buscar a un detalle cotizacion
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_cotizacion/{id}", method = GET)
    public Optional<Detalle_Cotizacion> getDetalle_Cotizacion(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_cotizacion/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Cotizacion nuevoDetalle_Cotizacion(@Valid @RequestBody Detalle_Cotizacion detalle_cotizacion) {
        repository.save(detalle_cotizacion);
        return detalle_cotizacion;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_cotizacion/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Cotizacion>> actualizarDetalle_Cotizacion(@Valid @PathVariable long id, @RequestBody Detalle_Cotizacion actualizarDetalle_Cotizacion) {
        Optional<Detalle_Cotizacion> detalle_cotizacion = repository.findById(id);
        if (detalle_cotizacion != null) {

            actualizarDetalle_Cotizacion.setId(id);
            repository.save(actualizarDetalle_Cotizacion);
            return new ResponseEntity<>(detalle_cotizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_cotizacion/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Cotizacion>> eliminarDetalle_Cotizacion(@PathVariable long id) {
        Optional<Detalle_Cotizacion> detalle_cotizacion = repository.findById(id);
        repository.deleteById(id);
        if (detalle_cotizacion != null) {
            return new ResponseEntity<>(detalle_cotizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //endpoint que devuelve a todos los detalle cotizacion por id de cotizacion
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_cotizacion/{id}/cotizacion", method = GET)
    public Collection<Detalle_Cotizacion> getDetailCotizable(@PathVariable long id) {
        return repository.DelailCotizable(id);
    }
//    // Taer los detalle cotizacion por id_cotizacion
//    @CrossOrigin
//    @RequestMapping(value = "/bodega/detalle_cotizacion/{id}/cotizacion", method = GET)
//    public Collection<Detalle_Cotizacion> getDetalleByCotizacion(@PathVariable long id) {
//        Collection<Detalle_Cotizacion> detalles = repository.findAll();
//        ArrayList cotizacionDetail = new ArrayList<>();
//
//        detalles.stream().filter((c) -> (c.getId_cotizacion().getId() == id)).forEachOrdered((c) -> {
//            cotizacionDetail.add(c);
//        });
//
//        return cotizacionDetail;
//    }
}

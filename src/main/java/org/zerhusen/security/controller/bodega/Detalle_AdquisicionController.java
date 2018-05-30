/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;
import org.zerhusen.model.bodega.Detalle_Adquisicion;
import org.zerhusen.model.bodega.Prestamo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import org.zerhusen.security.repository.bodega.Detalle_AdquisicionRepository;
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Ferenc
 */
@RestController
public class Detalle_AdquisicionController {
    /*@PostConstruct
	public void init() throws ParseException{
		repository.save(new Detalle_Adquisicion(2,3));
	}*/
    @Autowired
    private Detalle_AdquisicionRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_adquisicion/", method = GET)
    public Collection<Detalle_Adquisicion> getDetalle_Adquisiciones() {
        return repository.findAll();
    }
    
//    Traer los detalle prestamo según id de prestamo
//    @CrossOrigin
//    @RequestMapping(value = "/bodega/detalle_adquisicion/{id}/cotizacion", method = GET)
//    public Collection<Detalle_Adquisicion> getDetalleByCotizacion(@PathVariable long id) {
//       Collection<Detalle_Adquisicion> detalles = repository.findAll();
//       ArrayList cotizacionDetail = new ArrayList<>();
//       
//       for (Detalle_Adquisicion c: detalles) {
//           if (c.getId_cotizacion().getId() == id) {
//               cotizacionDetail.add(c);
//           }
//       }
//          
//        return cotizacionDetail;
//    }
    
//      @CrossOrigin
//    @RequestMapping(value = "/bodega/detalle_adquisicion/{id}/cotizacionFalse", method = GET)
//    public Collection<Detalle_Adquisicion> getDetalleByCotizacionFalse(@PathVariable long id) {
//       Collection<Detalle_Adquisicion> detalles = repository.findAll();
//       ArrayList cotizacionDetail = new ArrayList<>();
//       
//       for (Detalle_Adquisicion c: detalles) {
//           if (c.getId_cotizacion().getId() == id && c.isEstado() == false) {
//               cotizacionDetail.add(c);
//           }
//       }
//          
//        return cotizacionDetail;
//    }
//    
     //Buscar a un Detalle Adquisicion
   @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_adquisicion/{id}", method = GET)
    public Optional<Detalle_Adquisicion> getDetalle_Adquisicion(@PathVariable long id) {
        return repository.findById(id);
    }
          
     
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_adquisicion/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Adquisicion nuevoDetalle_Adquisicion(@Valid @RequestBody Detalle_Adquisicion detalle_adquisicion) {
        repository.save(detalle_adquisicion);

        return detalle_adquisicion;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_adquisicion/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Adquisicion>> actualizarDetalle_Adquisicion(@Valid @PathVariable long id, @RequestBody Detalle_Adquisicion actualizarDetalle_Adquisicion) {
        Optional<Detalle_Adquisicion> detalle_adquisicion = repository.findById(id);
        if (detalle_adquisicion != null) {

            actualizarDetalle_Adquisicion.setId(id);
            repository.save(actualizarDetalle_Adquisicion);
            return new ResponseEntity<>(detalle_adquisicion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_adquisicion/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Adquisicion>> eliminarDetalle_Adquisicion(@PathVariable long id) {
        Optional<Detalle_Adquisicion> detalle_adquisicion = repository.findById(id);
        repository.deleteById(id);
        if (detalle_adquisicion != null) {
            return new ResponseEntity<>(detalle_adquisicion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //endpoint que devuelve detalles adquicucion con ordenes de compra
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_adquisicion/{id}/orden", method = GET)
    public Collection<Detalle_Adquisicion> getDetalleByIdOrdenCompra(@PathVariable long id) {
//       Collection<Detalle_Adquisicion> detalles = repository.findAll();
//       ArrayList adquisicionDetail = new ArrayList<>();
//       
//       for (Detalle_Adquisicion c: detalles) {
//           if (c.getId_orden_compra().getId() == id) {
//               adquisicionDetail.add(c);
//           }
//       }
          
        return repository.adquisicionDetail(id);
    }
}

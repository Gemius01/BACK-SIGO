/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Cotizacion;
import org.zerhusen.security.repository.bodega.CotizacionRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import org.zerhusen.model.security.UserData;
import org.zerhusen.security.JwtTokenUtil;
import org.zerhusen.security.JwtUser;

/**
 *
 * @author Ferenc
 */
@RestController
public class CotizacionController {

    /*@PostConstruct
	public void init() throws ParseException{
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                 String dateInString = "07/06/2013";
             
                Date date = formatter.parse(dateInString);
		repository.save(new Cotizacion(date,"19415108-k"));
	}*/
    @Autowired
    private CotizacionRepository repository;
    
    UserData user = new UserData();  
    
    @Value("${jwt.header}")
    public String tokenHeader;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    public UserDetailsService userDetailsService;
    
    @Autowired
    public HttpServletRequest request;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/", method = GET)
    public Collection<Cotizacion> getCotizaciones() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }

    //Buscar a una Cotizacion
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/{id}", method = GET)
    public Optional<Cotizacion> getCotizacion(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cotizacion nuevaCotizacion(@Valid @RequestBody Cotizacion cotizacion) {
        repository.save(cotizacion);

        return cotizacion;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/{id}", method = PUT)
    public ResponseEntity<Optional<Cotizacion>> actualizarCotizacion(@Valid @PathVariable long id, @RequestBody Cotizacion actualizarCotizacion) {
        Optional<Cotizacion> cotizacion = repository.findById(id);
        if (cotizacion != null) {

            actualizarCotizacion.setId(id);
            repository.save(actualizarCotizacion);
            return new ResponseEntity<>(cotizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/{id}", method = DELETE)
    public ResponseEntity<Optional<Cotizacion>> eliminarCotizacion(@PathVariable long id) {
        Optional<Cotizacion> cotizacion = repository.findById(id);
        repository.deleteById(id);
        if (cotizacion != null) {
            return new ResponseEntity<>(cotizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //endpoint a una Cotizacion por id de orden de compra
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/{id}/orden_compra", method = GET)
    public Collection<Cotizacion> getCotizacionByOrden(@PathVariable long id) {
        return repository.CotizacionByOrden(id);
    }
//    // Consulta cotizacion por id de orden compra Cambiè a la antigua ya que no me devolvía todos los registros
//    @CrossOrigin
//    @RequestMapping(value = "/bodega/cotizacion/{id}/idorden_compra", method = GET)
//    public Collection<Cotizacion> getDetalleByCotizacion(@PathVariable long id) {
//        Collection<Cotizacion> detalles = repository.findAll();
//        ArrayList cotizacionDetail = new ArrayList<>();
//
//        for (Cotizacion c : detalles) {
//            if (c.getOrden_compra() == id) {
//                cotizacionDetail.add(c);
//            }
//        }
//
//        return cotizacionDetail;
//    }
    @CrossOrigin
    @RequestMapping(value = "/bodega/cotizacion/orden_compraTRUE", method = GET)
    public Collection<Cotizacion> getCotizacionByOrdenTrue() {
        return repository.CotizacionByOrdenTrue();
    }
}

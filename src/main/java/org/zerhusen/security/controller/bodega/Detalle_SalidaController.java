/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Detalle_Adquisicion;
import org.zerhusen.model.bodega.Detalle_Salida;
import org.zerhusen.security.repository.bodega.Detalle_SalidaRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class Detalle_SalidaController {
    /*@PostConstruct
	public void init() throws ParseException{
		repository.save(new Detalle_Salida(2,2));
	}*/
    @Autowired
    private Detalle_SalidaRepository repository;
    
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
    @RequestMapping(value = "/bodega/detalle_salida/", method = GET)
    public Collection<Detalle_Salida> getDetalle_Salidas() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }
    //Reporte Insumos con mayor movimiento
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/movimiento", method = GET)
    public Collection<Detalle_Salida> getReporteMovimiento() {
        
        Collection result = repository.reporteMovimientos( new PageRequest(0,7));
        return result;
    }
    //Reporte de mayor solicitante solicitante
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/mayorsolicitante", method = GET)
    public Collection<Detalle_Salida> getReporteMayorSolicitante() {
        
        Collection result = repository.reporteMayorSolicitante( new PageRequest(0,7));
        return result;
    }
    
     //Buscar a un Detalle Salida
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/{id}", method = GET)
    public Optional<Detalle_Salida> getDetalle_Salida(@PathVariable long id) {
        return repository.findById(id);
    }
    //Traer los detalles de salida por id salida
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/{id}/salida", method = GET)
    public Collection<Detalle_Salida> getDetalle_SalidaBySalida(@PathVariable long id) {
       Collection<Detalle_Salida> detalles = repository.findAll();
       ArrayList salidaDetail = new ArrayList<>();
       
       for (Detalle_Salida c: detalles) {
           if (c.getId_salida().getId() == id) {
               salidaDetail.add(c);
           }
       }
          
        return salidaDetail;
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Salida nuevoDetalle_Salida(@Valid @RequestBody Detalle_Salida detalle_salida) {
        repository.save(detalle_salida);

        return detalle_salida;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Salida>> actualizarDetalle_Salida(@Valid @PathVariable long id, @RequestBody Detalle_Salida actualizarDetalle_Salida) {
        Optional<Detalle_Salida> detalle_salida = repository.findById(id);
        if (detalle_salida != null) {

            actualizarDetalle_Salida.setId(id);
            repository.save(actualizarDetalle_Salida);
            return new ResponseEntity<>(detalle_salida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_salida/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Salida>> eliminarDetalle_Salida(@PathVariable long id) {
        Optional<Detalle_Salida> detalle_salida = repository.findById(id);
        repository.deleteById(id);
        if (detalle_salida != null) {
            return new ResponseEntity<>(detalle_salida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

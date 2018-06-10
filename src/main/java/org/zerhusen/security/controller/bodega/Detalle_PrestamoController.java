/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.zerhusen.model.bodega.Categoria;
import org.zerhusen.model.bodega.Detalle_Adquisicion;
import org.zerhusen.model.bodega.Detalle_Prestamo;
import org.zerhusen.model.bodega.Prestamo;
import org.zerhusen.model.bodega.SubCategoria;
import org.zerhusen.security.repository.bodega.Detalle_PrestamoRepository;
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
public class Detalle_PrestamoController {
    /*@PostConstruct
	public void init() throws ParseException{
		repository.save(new Detalle_Prestamo(2,2));
	}*/
    @Autowired
    private Detalle_PrestamoRepository repository;
    
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
    @RequestMapping(value = "/bodega/detalle_prestamo/", method = GET)
    public Collection<Detalle_Prestamo> getDetalle_Prestamos() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }
    
     //Buscar a un Detalle Prestamo
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_prestamo/{id}", method = GET)
    public Optional<Detalle_Prestamo> getDetalle_Prestamo(@PathVariable long id) {
        return repository.findById(id);
    }
    
    
    //Traer los detalle prestamo según id de prestamo
      @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_prestamo/{id}/prestamo", method = GET)
    public Collection<Detalle_Prestamo> getDetailByPrestamo(@PathVariable long id) {
//       Collection<Detalle_Prestamo> items = repository.findAll();
//       ArrayList itemsDetail = new ArrayList<>();
//       
//       for (Detalle_Prestamo c: items) {
//           if (c.getId_prestamo().getId() == id) {
//               itemsDetail.add(c);
//           }
//       }
//          
        return repository.DetailPrestamo(id);
    }
    //Traer todos los items prestados
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_prestamo/{id}/prestados", method = GET)
    public Collection<Detalle_Prestamo> getDetailByPrestados(@PathVariable long id) {
//       Collection<Detalle_Prestamo> items = repository.findAll();
//       ArrayList itemsDetail = new ArrayList<>();
//       
//       for (Detalle_Prestamo c: items) {
//           if (c.getId_prestamo().isDevolucion()==false) {
//               itemsDetail.add(c);
//           }
//       }
//          
        return repository.DetailByPrestados(id);
    }
    
    //Traer los detalle prestamo según id de prestamo
     
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_prestamo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Prestamo nuevoDetalle_Prestamo(@Valid @RequestBody Detalle_Prestamo detalle_prestamo) {
        repository.save(detalle_prestamo);

        return detalle_prestamo;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_prestamo/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Prestamo>> actualizarDetalle_Prestamo(@Valid @PathVariable long id, @RequestBody Detalle_Prestamo actualizarDetalle_Prestamo) {
        Optional<Detalle_Prestamo> detalle_prestamo = repository.findById(id);
        if (detalle_prestamo != null) {

            actualizarDetalle_Prestamo.setId(id);
            repository.save(actualizarDetalle_Prestamo);
            return new ResponseEntity<>(detalle_prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_prestamo/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Prestamo>> eliminarDetalle_Prestamo(@PathVariable long id) {
        Optional<Detalle_Prestamo> detalle_prestamo = repository.findById(id);
        repository.deleteById(id);
        if (detalle_prestamo != null) {
            return new ResponseEntity<>(detalle_prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

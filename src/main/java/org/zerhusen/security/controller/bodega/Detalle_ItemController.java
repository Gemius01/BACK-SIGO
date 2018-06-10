/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Detalle_Item;
import org.zerhusen.security.repository.bodega.Detalle_ItemRepository;
import java.util.Collection;
import java.util.Optional;
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
public class Detalle_ItemController {
    
    @Autowired
    private Detalle_ItemRepository repository;
    
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
    @RequestMapping(value = "/bodega/detalle_item/", method = GET)
    public Collection<Detalle_Item> getDetalles_Item() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }
    
    //Buscar a un Detalle Item
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_item/{id}", method = GET)
    public Optional<Detalle_Item> getDetalle_Item(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_item/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Item nuevoDetalle_Item(@Valid @RequestBody Detalle_Item detalle_item) {
        repository.save(detalle_item);
        return detalle_item;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_item/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Item>> actualizarDetalle_Item(@Valid @PathVariable long id, @RequestBody Detalle_Item actualizarDetalle_Item) {
        Optional<Detalle_Item> detalle_item = repository.findById(id);
        if (detalle_item != null) {

            actualizarDetalle_Item.setId(id);
            repository.save(actualizarDetalle_Item);
            return new ResponseEntity<>(detalle_item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_item/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Item>> eliminarDetalle_Item(@PathVariable long id) {
        Optional<Detalle_Item> detalle_item = repository.findById(id);
        repository.deleteById(id);
        if (detalle_item != null) {
            return new ResponseEntity<>(detalle_item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Encargado_Bodega;
import org.zerhusen.security.repository.bodega.Encargado_BodegaRepository;
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
 * @author don_w
 */
@RestController
public class Encargado_BodegaController {
    
    @Autowired
    private Encargado_BodegaRepository repository;
    
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
    @RequestMapping(value = "/bodega/encargado_bodega/", method = GET)
    public Collection<Encargado_Bodega> getEncargado_Bodega() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.lisaTodo(eluse.getId());
    }
    
    //Buscar a un Detalle Marca
    @CrossOrigin
    @RequestMapping(value = "/bodega/encargado_bodega/{id}", method = GET)
    public Optional<Encargado_Bodega> getEncargado_Bodega(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/encargado_bodega/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Encargado_Bodega nuevoEncargado_Bodega(@Valid @RequestBody Encargado_Bodega encargado_bodega) {
        repository.save(encargado_bodega);
        return encargado_bodega;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/encargado_bodega/{id}", method = PUT)
    public ResponseEntity<Optional<Encargado_Bodega>> actualizarEncargado_Bodega(@Valid @PathVariable long id, @RequestBody Encargado_Bodega actualizarEncargado_Bodega) {
        Optional<Encargado_Bodega> encargado_bodega = repository.findById(id);
        if (encargado_bodega != null) {

            actualizarEncargado_Bodega.setId(id);
            repository.save(actualizarEncargado_Bodega);
            return new ResponseEntity<>(encargado_bodega, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/encargado_bodega/{id}", method = DELETE)
    public ResponseEntity<Optional<Encargado_Bodega>> eliminarEncargado_Bodega(@PathVariable long id) {
        Optional<Encargado_Bodega> encargado_bodega = repository.findById(id);
        repository.deleteById(id);
        if (encargado_bodega != null) {
            return new ResponseEntity<>(encargado_bodega, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

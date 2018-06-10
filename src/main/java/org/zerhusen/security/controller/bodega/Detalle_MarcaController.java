/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

//comentario//
import org.zerhusen.model.bodega.Detalle_Item;
import org.zerhusen.model.bodega.Detalle_Marca;
import org.zerhusen.security.repository.bodega.Detalle_MarcaRepository;
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
public class Detalle_MarcaController {
    
    @Autowired
    private Detalle_MarcaRepository repository;
    
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
    @RequestMapping(value = "/bodega/detalle_marca/", method = GET)
    public Collection<Detalle_Marca> getDetalle_Marca() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }
    
    //Buscar a un Detalle Marca
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_marca/{id}", method = GET)
    public Optional<Detalle_Marca> getDetalle_Marca(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_marca/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Marca nuevoDetalle_Marca(@Valid @RequestBody Detalle_Marca detalle_marca) {
        repository.save(detalle_marca);
        return detalle_marca;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_marca/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Marca>> actualizarDetalle_Marca(@Valid @PathVariable long id, @RequestBody Detalle_Marca actualizarDetalle_Marca) {
        Optional<Detalle_Marca> detalle_marca = repository.findById(id);
        if (detalle_marca != null) {

            actualizarDetalle_Marca.setId(id);
            repository.save(actualizarDetalle_Marca);
            return new ResponseEntity<>(detalle_marca, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
     // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_marca/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Marca>> eliminarDetalle_Marca(@PathVariable long id) {
        Optional<Detalle_Marca> detalle_marca = repository.findById(id);
        repository.deleteById(id);
        if (detalle_marca != null) {
            return new ResponseEntity<>(detalle_marca, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

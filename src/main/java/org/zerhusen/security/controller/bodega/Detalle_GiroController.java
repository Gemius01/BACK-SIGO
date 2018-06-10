/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Detalle_Giro;
import org.zerhusen.security.repository.bodega.Detalle_GiroRepository;
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
public class Detalle_GiroController {
    /*@PostConstruct
	public void init() throws ParseException{
                Detalle_Giro det = new Detalle_Giro(2,"ProvePro");
		repository.save(det);
                
	}*/
    @Autowired
    private Detalle_GiroRepository repository;
    
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
    @RequestMapping(value = "/bodega/detalle_giro/", method = GET)
    public Collection<Detalle_Giro> getDetalle_Giros() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }
    
     //Buscar a un Detalle Giro
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_giro/{id}", method = GET)
    public Optional<Detalle_Giro> getDetalle_Giro(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_giro/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Detalle_Giro nuevoDetalle_Giro(@Valid @RequestBody Detalle_Giro detalle_giro) {
        repository.save(detalle_giro);

        return detalle_giro;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_giro/{id}", method = PUT)
    public ResponseEntity<Optional<Detalle_Giro>> actualizarDetalle_Giro(@Valid @PathVariable long id, @RequestBody Detalle_Giro actualizarDetalle_Giro) {
        Optional<Detalle_Giro> detalle_giro = repository.findById(id);
        if (detalle_giro != null) {

            actualizarDetalle_Giro.setId(id);
            repository.save(actualizarDetalle_Giro);
            return new ResponseEntity<>(detalle_giro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_giro/{id}", method = DELETE)
    public ResponseEntity<Optional<Detalle_Giro>> eliminarDetalle_Giro(@PathVariable long id) {
        Optional<Detalle_Giro> detalle_giro = repository.findById(id);
        repository.deleteById(id);
        if (detalle_giro != null) {
            return new ResponseEntity<>(detalle_giro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin
    @RequestMapping(value = "/bodega/detalle_giro/{rut}/proveedor", method = GET)
    public Collection<Detalle_Giro> getDetalleByCotizacion(@PathVariable String rut) {
//       Collection<Detalle_Giro> detalles = repository.listaTodo();
//       ArrayList cotizacionDetail = new ArrayList<>();
//       
//       for (Detalle_Giro c: detalles) {
//           if (c.getRut_proveedor().getRut().equals(rut)) {
//               cotizacionDetail.add(c);
//           }
//       }
          
        return repository.cotizacionDetail(rut);
    }
}

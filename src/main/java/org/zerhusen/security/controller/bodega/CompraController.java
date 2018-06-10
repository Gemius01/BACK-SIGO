/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.zerhusen.model.bodega.Compra;
import org.zerhusen.security.repository.bodega.CompraRepository;
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
public class CompraController {

    /*@PostConstruct
	public void init() throws ParseException{
            //Arreglar DATE 
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
             String dateInString = "2013/05/17";
             Date date = formatter.parse(dateInString);
             repository.save(new Compra(date,2));	
	}*/
    Gson gson = new Gson();

    @Autowired
    private CompraRepository repository;
    
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
    @RequestMapping(value = "/bodega/compra/", method = GET)
    public Collection<Compra> getCompras() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }

    //Reporte de Compras con recepcion Pendinetes
    @CrossOrigin
    @RequestMapping(value = "/bodega/compra/recepcionPendiente", method = GET)
    public Collection<Compra> getComprasRecepcionPendiente() {
        return repository.listaReporteComprasRecepcionPendiente();
    }

    //Buscar a una Compra
    @CrossOrigin
    @RequestMapping(value = "/bodega/compra/{id}", method = GET)
    public Optional<Compra> getCompra(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/compra/", method = POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String nuevaCompra(@Valid @RequestBody Compra compra) {

        JsonObject obj = new JsonObject();

        Compra lacompra = repository.elNFactura(compra.getNumFactura());
        if (lacompra == null) {
            repository.save(compra);
            obj.addProperty("id", compra.getId());
            return obj.toString();
        } else {
            obj.addProperty("defaultMessage", "Numero Factura ya existe");
            return obj.toString();
        }
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/compra/{id}", method = PUT)
    public ResponseEntity<Optional<Compra>> actualizarCompra(@Valid @PathVariable long id, @RequestBody Compra actualizarCompra) {
        Optional<Compra> compra = repository.findById(id);
        if (compra != null) {

            actualizarCompra.setId(id);
            repository.save(actualizarCompra);
            return new ResponseEntity<>(compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/compra/{id}", method = DELETE)
    public ResponseEntity<Optional<Compra>> eliminarCompra(@PathVariable long id) {
        Optional<Compra> compra = repository.findById(id);
        repository.deleteById(id);
        if (compra != null) {
            return new ResponseEntity<>(compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //endpoint que me devuelve todas las compras segùn el rut del proveedor
    @CrossOrigin
    @RequestMapping(value = "/bodega/compra/{rut}/proveedor", method = GET)
    public Collection<Compra> getDetalleByCotizacion(@PathVariable String rut) {
        Collection<Compra> compras = repository.findAll();
        ArrayList compraDetail = new ArrayList<>();

        compras.stream().filter((c) -> (rut.equals(c.getId_orden_compra().getId_cotizacion().getRut_proveedor().getRut()))).forEachOrdered((c) -> {
            compraDetail.add(c);
        });

        return compraDetail;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.zerhusen.model.bodega.Bodega;
import org.zerhusen.model.bodega.Ciudad;
import org.zerhusen.model.bodega.Cotizacion;
import org.zerhusen.model.bodega.Detalle_Giro;
import org.zerhusen.model.bodega.Giro;
import org.zerhusen.model.bodega.Orden_Compra;
import org.zerhusen.model.bodega.Pais;
import org.zerhusen.model.bodega.Proveedor;
import org.zerhusen.model.bodega.Region;
import org.zerhusen.model.user.E_Cliente;
import org.zerhusen.model.security.ValiRut;
import org.zerhusen.security.repository.bodega.PaisRepository;
import org.zerhusen.security.repository.bodega.ProveedorRepository;
import org.zerhusen.security.repository.bodega.RegionRepository;
import org.zerhusen.security.repository.bodega.CiudadRepository;
import org.zerhusen.security.repository.bodega.CotizacionRepository;
import org.zerhusen.security.repository.bodega.Detalle_GiroRepository;
import org.zerhusen.security.repository.bodega.GiroRepository;
import org.zerhusen.security.repository.bodega.Orden_CompraRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.model.security.UserData;
import org.zerhusen.security.JwtTokenUtil;
import org.zerhusen.security.JwtUser;
import org.zerhusen.security.controller.UserRestController;
import org.zerhusen.security.repository.UserRepository;
import org.zerhusen.security.repository.user.E_ClienteRepository;

/**
 *
 * @author Ferenc
 */
@RestController
public class ProveedorController {

    ValiRut valirut = new ValiRut();
    Gson gson = new Gson();

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
   
//    @PostConstruct
//    public void init() throws ParseException {
//        if (repository.findAll().isEmpty() == true) {
//            //Creacion de un pais
//            Pais pais = new Pais("PaisDefault");
//            Paisrepository.save(pais);
//            //Creacion de una region
//            Region region = new Region("RegionDefault", pais);
//            Regionrepository.save(region);
//            //Creacion de una ciudad
//            Ciudad ciudad = new Ciudad("CiudadDefault", region);
//            Ciudadrepository.save(ciudad);
//            //Crear una bodega
//            Bodega bodega = new Bodega();
//            //Crear una empresa cliente
//            E_Cliente e_cliente = new E_Cliente("99999999-9","NomDefault","DirDefault","+5987654321","emai@default.com","ContDefault");
//            //Creacion de un Proveedor
//            Proveedor proveedor = new Proveedor("11111111-1", "Default", "Default", "Default", ciudad, "+12345678910", "+12345678910", "Default@Default.com", true, e_cliente);
//            repository.save(proveedor);
//            //Creacion de un Giro
//            Giro giro = new Giro(1,"Default");
//            Girorepository.save(giro);
//            //Creacion de un Detalle_Giro
//            Detalle_Giro detalle_giro = new Detalle_Giro(giro, proveedor);
//            Detalle_Girorepository.save(detalle_giro);
//            //Creacion de una Cotizacion
//            Calendar calendario = GregorianCalendar.getInstance();
//            Date fechaHoy = calendario.getTime();
//            //Creacion de Orden_Compra
//            Orden_Compra orden_compra = new Orden_Compra(fechaHoy, null, true);
//            Orden_Comprarepository.save(orden_compra);
//            //Crear una cotizacion
//            Cotizacion cotizacion = new Cotizacion(fechaHoy, proveedor, 0, 1, true);
//            Cotizacionrepository.save(cotizacion);
//            
//        }
//    }
    @Autowired
    private ProveedorRepository repository;
    
    

    // Petición GET (Mostrar Todos x empresa)
    @CrossOrigin
    @RequestMapping(value = "/bodega/proveedor/", method = GET)
    public Collection<Proveedor> getProveedors() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }

    //Buscar a un Proveedor
    @CrossOrigin
    @RequestMapping(value = "/bodega/proveedor/{rut}", method = GET)
    public Optional<Proveedor> getProveedor(@PathVariable String rut) {
        return repository.findById(rut);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/proveedor/", method = POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String nuevoProveedor(@Valid @RequestBody Proveedor proveedor) {

        JsonObject in_rut = new JsonObject();
        JsonObject in_ext = new JsonObject();
        JsonObject in_len = new JsonObject();
        JsonObject object1 = new JsonObject();
        JsonObject object2 = new JsonObject();

        if (repository.findById(proveedor.getRut()) != null) {
            in_ext.addProperty("defaultMessage", "Usuario ya Existe");
            String json_ext = in_ext.toString();
            return json_ext;
        } else if (proveedor.isNacional() != true){
                if(proveedor.getRut().length() <= 3){
                  in_len.addProperty("defaultMessage", "NIC Empresarial muy corto");
                  String json_len = in_len.toString();
                  return json_len;  
                }else{
                    repository.save(proveedor);
                    object1.addProperty("rut", proveedor.getRut());
                    String json_object1 = object1.toString();
                    return json_object1;
                }
        } else if (valirut.validarRut(proveedor.getRut())) {
            repository.save(proveedor);
            object2.addProperty("rut", proveedor.getRut());
            String json_object2 = object2.toString();
            return json_object2;
        } else {
            in_rut.addProperty("defaultMessage", "Rut Invalido");
            String json_rut = in_rut.toString();
            return json_rut;
        }
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/proveedor/{rut}", method = PUT)
    public ResponseEntity<Optional<Proveedor>> actualizarProveedor(@Valid @PathVariable String rut, @RequestBody Proveedor actualizarProveedor) {
        Optional<Proveedor> proveedor = repository.findById(rut);
        if (proveedor != null) {
            actualizarProveedor.setRut(rut);
            repository.save(actualizarProveedor);
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/proveedor/{rut}", method = DELETE)
    public ResponseEntity<Optional<Proveedor>> eliminarProveedor(@PathVariable String rut) {
        Optional<Proveedor> proveedor = repository.findById(rut);
        repository.deleteById(rut);
        if (proveedor != null) {
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //endpoint que devuelve todos los proveedores nacionales 
    @CrossOrigin
    @RequestMapping(value = "/bodega/provNaciTrue/", method = GET)
    public Collection<Proveedor> getProveedorNacionalTrue() {

            return repository.listaNacional("1");
        
    }
    
    //endpoint que devuelve todos los proveedores internacionales
    @CrossOrigin
    @RequestMapping(value = "/bodega/provNaciFalse/", method = GET)
    public Collection<Proveedor> getProveedorNacionalFalse() {

            return repository.listaNacional("0");
   
    }
}

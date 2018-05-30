/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Categoria;
import org.zerhusen.model.bodega.Ciudad;
import org.zerhusen.model.bodega.Pais;
import org.zerhusen.model.bodega.Region;
import org.zerhusen.model.bodega.SubCategoria;
import org.zerhusen.model.bodega.Tipo;
import org.zerhusen.security.repository.bodega.PaisRepository;
import org.zerhusen.security.repository.bodega.RegionRepository;
import org.zerhusen.security.repository.bodega.CiudadRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/**
 *
 * @author Ferenc
 */
@RestController
public class CiudadController {
    @Autowired
    private PaisRepository Paisrepository;
    
    @Autowired
    private RegionRepository Regionrepository;
    
    @PostConstruct
    public void init() {
        if (repository.findAll().isEmpty() == true) {
            //Creacion del Pais
            Pais pais = new Pais("Chile");
            Paisrepository.save(pais);
            //Creacion de ciudades y regiones
            //XV REGION
            Region XVregion = new Region("XV Arica y Parinacota", pais);
            Regionrepository.save(XVregion);
            Ciudad XVciudad = new Ciudad("Arica", XVregion);
            repository.save(XVciudad);
            //I REGION
            Region Iregion = new Region("I Tarapaca", pais);
            Regionrepository.save(Iregion);
            Ciudad Iciudad = new Ciudad("Iquique", Iregion);
            repository.save(Iciudad);
            //II REGION
            Region IIregion = new Region("II Antofagasta", pais);
            Regionrepository.save(IIregion);
            Ciudad IIciudad = new Ciudad("Antofagasta", IIregion);
            repository.save(IIciudad);
            //III REGION
            Region IIIregion = new Region("III Atacama", pais);
            Regionrepository.save(IIIregion);
            Ciudad IIIciudad = new Ciudad("Copiapo", IIIregion);
            repository.save(IIIciudad);
            //IV REGION
            Region IVregion = new Region("IV Coquimbo", pais);
            Regionrepository.save(IVregion);
            Ciudad IVciudad = new Ciudad("La Serena", IVregion);
            repository.save(IVciudad);
            //V REGION
            Region Vregion = new Region("V Valparaíso", pais);
            Regionrepository.save(Vregion);
            Ciudad Vciudad = new Ciudad("Valparaíso", Vregion);
            repository.save(Vciudad);
            //RM REGION
            Region RMregion = new Region("RM Metropolitana", pais);
            Regionrepository.save(RMregion);
            Ciudad RMciudad = new Ciudad("Santiago", RMregion);
            repository.save(RMciudad);
            //VI REGION
            Region VIregion = new Region("VI O’Higgins", pais);
            Regionrepository.save(VIregion);
            Ciudad VIciudad = new Ciudad("Rancagua", VIregion);
            repository.save(VIciudad);
            //VII REGION
            Region VIIregion = new Region("VII Maule", pais);
            Regionrepository.save(VIIregion);
            Ciudad VIIciudad = new Ciudad("Talca", VIIregion);
            repository.save(VIIciudad);
            //VIII REGION
            Region VIIIregion = new Region("VIII Bio Bio", pais);
            Regionrepository.save(VIIIregion);
            Ciudad VIIIciudad = new Ciudad("Concepción", VIIIregion);
            repository.save(VIIIciudad);
            //IX REGION
            Region IXregion = new Region("IX Araucanía", pais);
            Regionrepository.save(IXregion);
            Ciudad IXciudad = new Ciudad("Temuco", IXregion);
            repository.save(IXciudad);
            //XIV REGION
            Region XIVregion = new Region("XIV Los Ríos", pais);
            Regionrepository.save(XIVregion);
            Ciudad XIVciudad = new Ciudad("Valdivia", XIVregion);
            repository.save(XIVciudad);
            //X REGION
            Region Xregion = new Region("X Los Lagos", pais);
            Regionrepository.save(Xregion);
            Ciudad Xciudad = new Ciudad("Puerto Montt", Xregion);
            repository.save(Xciudad);
            //XI REGION
            Region XIregion = new Region("XI Aysén", pais);
            Regionrepository.save(XIregion);
            Ciudad XIciudad = new Ciudad("Coyhaique", XIregion);
            repository.save(XIciudad);
            //XII REGION
            Region XIIregion = new Region("XII Magallanes", pais);
            Regionrepository.save(XIIregion);
            Ciudad XIIciudad = new Ciudad("Punta Arenas", XIIregion);
            repository.save(XIIciudad); 
        }
    }
    
    @Autowired
    private CiudadRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/ciudad/", method = GET)
    public Collection<Ciudad> getCiudades() {
        return repository.listaTodo();
    }
    
    //Buscar a una Ciudad
    @CrossOrigin
    @RequestMapping(value = "/bodega/ciudad/{id}", method = GET)
    public Optional<Ciudad> getCiudad(@PathVariable long id) {
        return repository.findById(id);
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/ciudad/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Ciudad nuevaCiudad(@Valid @RequestBody Ciudad ciudad) {
        
        repository.save(ciudad);

        return ciudad;
    }
    
    @CrossOrigin
    @RequestMapping(value = "/bodega/ciudad/{id}/region", method = GET)
    public Collection<Ciudad> getCityByRegion(@PathVariable long id) {
//       Collection<Ciudad> ciudades = repository.findAll();
//       ArrayList ciudadesType = new ArrayList<>();
//       
//       for (Ciudad c: ciudades) {
//           if (c.getId_region().getId() == id) {
//               ciudadesType.add(c);
//           }
//       }
//          
//        return ciudadesType;
        return repository.listaCityByRegion(id);
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/ciudad/{id}", method = PUT)
    public ResponseEntity<Optional<Ciudad>> actualizarCiudad(@Valid @PathVariable long id, @RequestBody Ciudad actualizarCiudad) {
        Optional<Ciudad> ciudad = repository.findById(id);
        if (ciudad != null) {

            actualizarCiudad.setId(id);
            repository.save(actualizarCiudad);
            return new ResponseEntity<>(ciudad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/ciudad/{id}", method = DELETE)
    public ResponseEntity<Optional<Ciudad>> eliminarCiudad(@PathVariable long id) {
        Optional<Ciudad> ciudad = repository.findById(id);
        repository.deleteById(id);
        if (ciudad != null) {
            return new ResponseEntity<>(ciudad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }  
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Categoria;
import org.zerhusen.model.bodega.Cotizacion;
import org.zerhusen.model.bodega.SubCategoria;
import org.zerhusen.security.repository.bodega.SubCategoriaRepository;
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
public class SubCategoriaController {

    /*@PostConstruct
    public void init() throws ParseException{         
  repository.save(new SubCategoria("Canal"));
 }*/
    @Autowired
    private SubCategoriaRepository repository;
    
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
    @RequestMapping(value = "/bodega/subcategoria/", method = GET)
    public Collection<SubCategoria> getSubCategorias() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }

    //Buscar a una Sub Categoria
    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/{id}", method = GET)
    public Optional<SubCategoria> getSubCategoria(@PathVariable long id) {
        return repository.findById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/{id}/categoria", method = GET)
    public Collection<SubCategoria> getSubCategoriaByCategoria(@PathVariable long id) {
//         Collection<SubCategoria> subcategories = repository.findAll();
//       ArrayList subcategoriesType = new ArrayList<>();
//       
//       for (SubCategoria c: subcategories) {
//           if (c.getId_categoria().getId() == id) {
//               subcategoriesType.add(c);
//           }
//       }
//          
//        return subcategoriesType;
        return repository.SubCategoriaByCategoria(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/tipo/herramienta", method = GET)
    public Collection<Categoria> getSubCategoriaByTipoH() {
        Collection<SubCategoria> subcategories = repository.findAll();
        ArrayList subcategoriesType = new ArrayList<>();

        for (SubCategoria c : subcategories) {
            if (c.getId_categoria().getId_tipo().getId() == 1) {
                subcategoriesType.add(c);
            }
        }

        return subcategoriesType;
    }

    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/tipo/insumo", method = GET)
    public Collection<SubCategoria> getSubCategoriaByTipoI() {
        Collection<SubCategoria> subcategories = repository.findAll();
        ArrayList subcategoriesType = new ArrayList<>();

        for (SubCategoria c : subcategories) {
            if (c.getId_categoria().getId_tipo().getId() == 2) {
                subcategoriesType.add(c);
            }
        }

        return subcategoriesType;
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public SubCategoria nuevaSubCategoria(@Valid @RequestBody SubCategoria subcategoria) {
        repository.save(subcategoria);
        System.out.println(subcategoria);
        return subcategoria;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/{id}", method = PUT)
    public ResponseEntity<Optional<SubCategoria>> actualizarSubCategoria(@Valid @PathVariable long id, @RequestBody SubCategoria actualizarSubCategoria) {
        Optional<SubCategoria> subcategoria = repository.findById(id);
        if (subcategoria != null) {

            actualizarSubCategoria.setId(id);
            repository.save(actualizarSubCategoria);
            return new ResponseEntity<>(subcategoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/subcategoria/{id}", method = DELETE)
    public ResponseEntity<Optional<SubCategoria>> eliminarSubCategoria(@PathVariable long id) {
        Optional<SubCategoria> subcategoria = repository.findById(id);
        repository.deleteById(id);
        if (subcategoria != null) {
            return new ResponseEntity<>(subcategoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

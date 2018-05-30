/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.zerhusen.model.bodega.Categoria;
import org.zerhusen.security.repository.bodega.CategoriaRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
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

/**
 *
 * @author Ferenc
 */
@RestController
public class CategoriaController {

    /*@PostConstruct
    public void init() {
        repository.save(new Categoria("Canales"));
    }*/
    @Autowired
    private CategoriaRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/", method = GET)
    public Collection<Categoria> getCategorias() {
        return repository.listaTodo();
    }

    //Buscar a una Categoria
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/{id}", method = GET)
    public Optional<Categoria> getCategoria(@PathVariable long id) {
        return repository.findById(id);
    }
    
    /*Buscar a una Categoria
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/{id}/tipo", method = GET)
    public Collection<Categoria> getCategoriaByTipo(@PathVariable long id) {
       Collection<Categoria> categories = repository.findAll();
       ArrayList categoriesType = new ArrayList<>();
       
       for (Categoria c: categories) {
           if (c.getId_tipo().getId() == id) {
               categoriesType.add(c);
           }
       }
        return categoriesType;
    }
*/
    //Buscar a una Categoria por tipo Herramienta
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/tipo/herramienta", method = GET)
    public Collection<Categoria> getCategoriaByTipoHerramienta() {
       /*Collection<Categoria> categoriesH = repository.findAll();
       ArrayList categoriesTypeH = new ArrayList<>();
       
       categoriesH.stream().filter((c) -> ("Herramienta".equals(c.getId_tipo().getNombre()))).forEachOrdered((c) -> {
           categoriesTypeH.add(c);
        });
        return categoriesTypeH;*/
       return repository.listaInsumosoHerramientas("Herramienta");
    }
    //Buscar a una Categoria por tipo Insumo
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/tipo/insumo", method = GET)
    public Collection<Categoria> getCategoriaByTipoInsumo() {
       /*Collection<Categoria> categoriesI = repository.findAll();
       ArrayList categoriesTypeI = new ArrayList<>();
       
       categoriesI.stream().filter((c) -> ("Insumo".equals(c.getId_tipo().getNombre()))).forEachOrdered((c) -> {
           categoriesTypeI.add(c);
        });
        return categoriesTypeI;*/
       return repository.listaInsumosoHerramientas("Insumo");
    }

    // Petición POST(Agregar Categoria)
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria nuevaCategoria(@Valid @RequestBody Categoria categoria) {
        repository.save(categoria);
        return categoria;
    }

    // Petición PUT (Editar Categoria)
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/{id}", method = PUT)
    public ResponseEntity<Optional<Categoria>> actualizarCategoria(@Valid @PathVariable long id, @RequestBody Categoria actualizarCategoria) {
        Optional<Categoria> categoria = repository.findById(id);
        if (categoria != null) {

            actualizarCategoria.setId(id);
            repository.save(actualizarCategoria);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar Categoria)
    @CrossOrigin
    @RequestMapping(value = "/bodega/categoria/{id}", method = DELETE)
    public ResponseEntity<Optional<Categoria>> eliminarCategoria(@PathVariable long id) {
        Optional<Categoria> categoria = repository.findById(id);
        repository.deleteById(id);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

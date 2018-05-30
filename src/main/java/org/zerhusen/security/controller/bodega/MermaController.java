/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Item;
import org.zerhusen.model.bodega.Merma;
import org.zerhusen.security.repository.bodega.MermaRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class MermaController {

    /*@PostConstruct
    public void init() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "07/06/2013";

        Date date = formatter.parse(dateInString);
        repository.save(new Merma(date, "se echó a perder", "tito corleone", 3, 5));

    }*/
    @Autowired
    private MermaRepository repository;

    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/", method = GET)
    public Collection<Merma> getMermas() {
        return repository.findAll();
    }
    
    //Buscar a una Merma
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/{id}", method = GET)
    public Optional<Merma> getMerma(@PathVariable long id) {
        return repository.findById(id);
    }
    
    //Endpoint que devuelve todas las mermas anuladas anulado
    @CrossOrigin
    @RequestMapping(value = "/bodega/mermaTrue/", method = GET)
    public Collection<Merma> getMermasTrue() {
        return repository.listaAnulados();
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Merma nuevaMerma(@Valid @RequestBody Merma merma) {
        repository.save(merma);

        return merma;
    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/{id}", method = PUT)
    public ResponseEntity<Optional<Merma>> actualizarMerma(@Valid @PathVariable long id, @RequestBody Merma actualizarMerma) {
        Optional<Merma> merma = repository.findById(id);
        if (merma != null) {

            actualizarMerma.setId(id);
            repository.save(actualizarMerma);
            return new ResponseEntity<>(merma, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/{id}", method = DELETE)
    public ResponseEntity<Optional<Merma>> eliminarMerma(@PathVariable long id) {
        Optional<Merma> merma = repository.findById(id);
        repository.deleteById(id);
        if (merma != null) {
            return new ResponseEntity<>(merma, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/{fi},{ff}/insumoSnAnulada", method = GET)
    public Collection<Merma> getMermaByinsumoSnCotizacion(@PathVariable String fi, @PathVariable String ff) {
        Collection<Merma> merma = repository.mermSnAnulada(fi, ff);
        ArrayList insumos = new ArrayList<>();

        merma.stream().filter((c) -> ("Insumo".equals(c.getId_item().getId_subcategoria().getId_categoria().getId_tipo().getNombre()) && c.isAnulado() != true)).forEachOrdered((c) -> {
            insumos.add(c);
        });
        return insumos;
    }
    
    @CrossOrigin
    @RequestMapping(value = "/bodega/merma/{fi},{ff}/herraSnAnulada", method = GET)
    public Collection<Merma> getMermaByherraSnCotizacion(@PathVariable String fi, @PathVariable String ff){
        System.out.println(fi);
        System.out.println(ff);
        Collection<Merma> merma = repository.mermSnAnulada(fi,ff);
        
        ArrayList herramienta = new ArrayList<>();

        merma.stream().filter((c) -> ("Herramienta".equals(c.getId_item().getId_subcategoria().getId_categoria().getId_tipo().getNombre()) && c.isAnulado() != true)).forEachOrdered((c) -> {
            herramienta.add(c);
        });
        return herramienta;
    }
      
}

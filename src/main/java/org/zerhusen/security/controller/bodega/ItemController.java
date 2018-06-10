/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.bodega;

import org.zerhusen.model.bodega.Categoria;
import org.zerhusen.model.bodega.Item;
import org.zerhusen.model.bodega.Marca;
import org.zerhusen.model.bodega.Prestamo;
import org.zerhusen.model.bodega.SubCategoria;
import org.zerhusen.model.bodega.Tipo;
import org.zerhusen.model.bodega.Tipo_Unidad;
import org.zerhusen.model.bodega.Unidad_Medida;
import org.zerhusen.model.user.E_Cliente;
import org.zerhusen.security.repository.bodega.CategoriaRepository;
import org.zerhusen.security.repository.bodega.ItemRepository;
import org.zerhusen.security.repository.bodega.MarcaRepository;
import org.zerhusen.security.repository.bodega.SubCategoriaRepository;
import org.zerhusen.security.repository.bodega.TipoRepository;
import org.zerhusen.security.repository.bodega.Tipo_UnidadRepository;
import org.zerhusen.security.repository.bodega.Unidad_MedidaRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
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
public class ItemController {

    
    @Autowired
    private TipoRepository Tiporepository;
    @Autowired
    private CategoriaRepository Categoriarepository;
    @Autowired
    private SubCategoriaRepository SubCategoriarepository;
    @Autowired
    private Unidad_MedidaRepository Unidad_Medidarepository;
    @Autowired
    private Tipo_UnidadRepository Tipo_Unidadrepository;
    @Autowired
    private MarcaRepository MarcaRepository;

//    @PostConstruct
//    public void init() {
//        if (repository.findAll().isEmpty() == true) {
//            //Creacion de tipos
//            Tiporepository.save(new Tipo("Herramienta"));
//            Tiporepository.save(new Tipo("Insumo"));
//            Tipo tipo = new Tipo("Default");
//            Tiporepository.save(tipo);
//            //Crear una empresa cliente
//            E_Cliente e_cliente = new E_Cliente("11111111-1","NomDefault","DirDefault","+5987654321","emai@default.com","ContDefault");
//            //Creacion de una categoria
//            Categoria categoria = new Categoria("CatDefault", tipo, e_cliente);
//            Categoriarepository.save(categoria);
//            //Creacion de una SubCategoria
//            SubCategoria subcategoria = new SubCategoria("SubCatDefault", categoria);
//            SubCategoriarepository.save(subcategoria);
//            //Creacion de Tipo_Unidad
//            Tipo_Unidad tipo_unidad = new Tipo_Unidad("Unidad");
//            Tipo_Unidadrepository.save(tipo_unidad);
//            //Creacion de Unidad_Medida
//            Unidad_Medida unidad_medida = new Unidad_Medida("Unidad", tipo_unidad);
//            Unidad_Medidarepository.save(unidad_medida);
//            //Creacion de una Marca
//            Marca marca = new Marca("MarDefault");
//            MarcaRepository.save(marca);
//            //Creacion de un Item
//            repository.save(new Item("Default", marca, "Default", 0, 0, unidad_medida, subcategoria));  
//        }	
//    }

    @Autowired
    private ItemRepository repository;
    
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
    @RequestMapping(value = "/bodega/item/", method = GET)
    public Collection<Item> getItems() {
        JwtUser eluse = user.getAuthenticatedUser(tokenHeader,jwtTokenUtil,userDetailsService,request);
        return repository.listaTodo(eluse.getId());
    }
    
    //Reporte de Insumos con stock bajo
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/insumosBajos", method = GET)
    public Collection<Item> getInsumosBajos() {
        return repository.listaReporteInsumoBajo();
    }

    //Buscar a un Item
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/{id}", method = GET)
    public Optional<Item> getItem(@PathVariable long id) {
        return repository.findById(id);
    }

    //Traer todos los items que tengan como tipo "Herramienta"
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/herramienta", method = GET)
    public Collection<Item> getItemByHerramienta() {
        Collection<Item> items = repository.findAll();
        ArrayList herramientas = new ArrayList<>();

        items.stream().filter((c) -> ("Herramienta".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre()))).forEachOrdered((c) -> {
            herramientas.add(c);
        });
        
        return herramientas;
        //return repository.ItemsByTipos("Herramienta");
    }

    //Traer todos los items que tengan como tipo "Insumo"
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/insumo", method = GET)
    public Collection<Item> getItemByinsumo() {
        Collection<Item> items = repository.findAll();
        ArrayList insumos = new ArrayList<>();

        items.stream().filter((c) -> ("Insumo".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre()))).forEachOrdered((c) -> {
            insumos.add(c);
        });
        return insumos;
    }

    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)

    public Item nuevoItem(@Valid @RequestBody Item item) {

        repository.save(item);

        return item;

    }

    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/{id}", method = PUT)
    public ResponseEntity<Optional<Item>> actualizarItem(@Valid @PathVariable long id, @RequestBody Item actualizarItem) {
        Optional<Item> item = repository.findById(id);
        if (item != null) {

            actualizarItem.setId(id);
            repository.save(actualizarItem);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/{id}", method = DELETE)
    public ResponseEntity<Optional<Item>> eliminarItem(@PathVariable long id) {
        Optional<Item> item = repository.findById(id);
        repository.deleteById(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //reporte todas los items herramientas con stok mayor a 0 
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/reportHerramientaMay0", method = GET)
    public Collection<Item> getItemByHerramientaByStockMay0() {
        Collection<Item> items = repository.findAll();
        ArrayList herramientas = new ArrayList<>();

        items.stream().filter((c) -> ("Herramienta".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre()) && c.getStock() > 0)).forEachOrdered((c) -> {
            herramientas.add(c);
        });
        
        return herramientas;
        //return repository.ItemsByTipos("Herramienta");
    }
    
    //reporte de items herramientas con stok en 0
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/reportHerramienta0", method = GET)
    public Collection<Item> getItemByHerramientaByStock0() {
        Collection<Item> items = repository.findAll();
        ArrayList herramientas = new ArrayList<>();

        items.stream().filter((c) -> ("Herramienta".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre()) && c.getStock() == 0)).forEachOrdered((c) -> {
            herramientas.add(c);
        });
        
        return herramientas;
        //return repository.ItemsByTipos("Herramienta");
    }
    
    //reporte de item herramienta con stok critico
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/reportHerramientaCritic", method = GET)
    public Collection<Item> getItemByHerramientaByStockCritic() {
        Collection<Item> items = repository.findAll();
        ArrayList herramientas = new ArrayList<>();

        items.stream().filter((c) -> ("Herramienta".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre()) && c.getStock() < c.getStockCritico())).forEachOrdered((c) -> {
            herramientas.add(c);
        });
        
        return herramientas;
        //return repository.ItemsByTipos("Herramienta");
    }
    
    //reporte de items insumos con trok mayor a 0
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/reportInsumoMay0", method = GET)
    public Collection<Item> getItemByInsumoByStockMay0() {
        Collection<Item> items = repository.findAll();
        ArrayList insumos = new ArrayList<>();

        items.stream().filter((c) -> ("Insumo".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre())&& c.getStock() > 0)).forEachOrdered((c) -> {
            insumos.add(c);
        });
        return insumos;
    }
    
    //reporte de items insumos con trok en 0
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/reportInsumo0", method = GET)
    public Collection<Item> getItemByInsumoByStock0() {
        Collection<Item> items = repository.findAll();
        ArrayList insumos = new ArrayList<>();

        items.stream().filter((c) -> ("Insumo".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre())&& c.getStock() == 0)).forEachOrdered((c) -> {
            insumos.add(c);
        });
        return insumos;
    }
    
    //reporte de items insumos con trok critico
    @CrossOrigin
    @RequestMapping(value = "/bodega/item/reportInsumoCritic", method = GET)
    public Collection<Item> getItemByInsumoByStockCritic() {
        Collection<Item> items = repository.findAll();
        ArrayList insumos = new ArrayList<>();

        items.stream().filter((c) -> ("Insumo".equals(c.getId_subcategoria().getId_categoria().getId_tipo().getNombre())&& c.getStock() < c.getStockCritico())).forEachOrdered((c) -> {
            insumos.add(c);
        });
        return insumos;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.security.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import org.zerhusen.model.user.Contrato;
import org.zerhusen.security.repository.user.ContratoRepository;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
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
import org.zerhusen.model.user.Pago;
import org.zerhusen.security.repository.user.PagoRepository;

/**
 *
 * @author Ferenc
 */
@RestController
public class ContratoController {
    
    @Autowired
    private ContratoRepository repository;
    
    @Autowired
    private PagoRepository pagoRepository;
    
    // Petición GET (Mostrar Todos)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/", method = GET)
    public Collection<Contrato> getContratos() {
        return repository.findAll();
    }

    //Buscar a un contrato
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{id}", method = GET)
    public Optional<Contrato> getContrato(@PathVariable long id) {
        return repository.findById(id);
    }
    
    // Petición POST(Agregar)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{diaPago}", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Contrato nuevoContrato(@Valid @RequestBody Contrato contrato, @PathVariable int diaPago) throws ParseException {
                //Date date = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();

        String dateAsString = sdf.format(now); //"08.01.2013"
        Date dateFromString = sdf.parse(dateAsString);
        
        String fechaTermino = sdf.format(contrato.getFecha_termino());
        
               
        contrato.setFecha(dateFromString);
        System.out.println(contrato.getFecha_termino());
        
        //String dataStr = "Mon Dec 12 00:00:00 GMT+00:00 2011";
        //SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date d = inputFormat.parse(contrato.getFecha_termino().toGMTString());
        //System.out.println(d);
        Calendar inicio = new GregorianCalendar();
            Calendar fin = new GregorianCalendar();
            inicio.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString));
            fin.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(fechaTermino));
            int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
            int difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);//Cantidad de Meses, Resta de Fecha Final - Fecha Inicio 
            //System.out.println(difM);
            //Date date = new Date();
            Double valorMembresia = (contrato.getValor_memb() * difM);
            System.out.println(valorMembresia);
            contrato.setValor_memb(valorMembresia);
        Contrato contratillo  = repository.save(contrato);
            long valorCuota = (long)(valorMembresia/ difM);
            difM = difM + 1;
            
            for (int i = 1; i < difM; i++) {
                //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + dateFromString);
                Calendar c1 = Calendar.getInstance();
                c1.setTime(dateFromString);//Setear la fecha de Inicio
                c1.set(Calendar.DAY_OF_MONTH, diaPago);//setear el Día de pago
                //System.out.println("--> " + c1.getTime());
                c1.add(Calendar.MONTH, i);//Setear el Mes de pago
                //System.out.println("--> + " + c1.getTime());

                Date dateee = c1.getTime();
                //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + dateFromString);
                Calendar c2 = Calendar.getInstance();
                c2.setTime(dateFromString);
                c2.set(Calendar.DAY_OF_MONTH, diaPago);
                //System.out.println("--> " + c2.getTime());
                int mesSiguiente = i + 1;
                c2.add(Calendar.MONTH, mesSiguiente);
                //System.out.println("--> + " + c2.getTime());

                Date dateeeVenc = c2.getTime();
                //Date dateVenc = c1.set(Calendar.MONTH, i++);
                //String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateee);
                //System.out.println(formattedDate);
                pagoRepository.save(new Pago(contratillo,dateeeVenc  ,dateee , valorCuota, 0, false));//Objeto de Pago
            }

        return contrato;
    }
    
    // Petición PUT (Editar)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{id}", method = PUT)
    public ResponseEntity<Optional<Contrato>> actualizarContrato(@Valid @PathVariable long id, @RequestBody Contrato actualizarContrato) {
        Optional<Contrato> contrato = repository.findById(id);
        if (contrato != null) {

            actualizarContrato.setId(id);
            repository.save(actualizarContrato);
            return new ResponseEntity<>(contrato, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Petición DELETE(Eliminar)
    @CrossOrigin
    @RequestMapping(value = "/user/contrato/{id}", method = DELETE)
    public ResponseEntity<Optional<Contrato>> eliminarContrato(@PathVariable long id) {
        Optional<Contrato> contrato = repository.findById(id);
        repository.deleteById(id);
        if (contrato != null) {
            return new ResponseEntity<>(contrato, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

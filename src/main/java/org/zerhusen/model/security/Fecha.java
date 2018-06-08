/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zerhusen.model.security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ferenc
 */
@RestController
public class Fecha {
    
    Gson gson = new Gson();

    //devolver solo fecha por back
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/fecha/", method = GET, produces = "application/json")
    public String getFecha(){
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha = dateFormat.format(new Date());
         
        System.out.println(dateFormat.format(new Date()));
        
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        System.out.println(month);
        System.out.println(localDate);
        //System.out.println(dateFormat.);
        JsonObject fechaHoy = new JsonObject();
        
        fechaHoy.addProperty("fecha", fecha);
        
        return fechaHoy.toString();
     
        //return null;
    }
    
    
    @CrossOrigin
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/testito/", method = GET, produces = "application/json")
    public Calendar getTest(){
        
        Date date = new Date();
        
         Calendar c1 = Calendar.getInstance();
            c1.setTime(date);
            System.out.println("--> " + c1.getTime());
            c1.add(Calendar.MONTH, 12);
            System.out.println("--> + " + c1.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //String fechaaa = sdf.format(c1);
            //System.out.println(fechaaa);
            Date dateee = c1.getTime();
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateee);
            System.out.println(formattedDate);
            return c1;
     
        //return null;
    }
}

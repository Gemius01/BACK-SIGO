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
import java.util.Date;
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
    @RequestMapping(value = "/fecha/", method = GET, produces = "application/json")
    public String getFecha(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha = dateFormat.format(new Date());
        
        JsonObject fechaHoy = new JsonObject();
        
        fechaHoy.addProperty("fecha", fecha);
        
        return fechaHoy.toString();
    }
}

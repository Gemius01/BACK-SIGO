package org.zerhusen.security.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.model.security.User;
import org.zerhusen.security.JwtTokenUtil;
import org.zerhusen.security.JwtUser;
import org.zerhusen.security.repository.UserRepository;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @CrossOrigin
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
   //Traer todos los usuarios
    @CrossOrigin
    @RequestMapping(value = "/user/todos/", method = GET)
    public Collection<JwtUser> allUser() {
        Collection<User> asd = userRepository.findAll();
         ArrayList lista = new ArrayList<>();
        for (User c : asd){
            System.out.println(c);
            System.out.println(c.getUsername());
            
            JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(c.getUsername());
            
            lista.add(user);
            
            
        }
        
        return lista;
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/nuevo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    
    //OBJ para agregar un Usuario
//     {
//        "username": "admineeeeeeee",
//        "password": "asdddddddd",
//        "enabled": true,
//        "lastPasswordResetDate": "",
//        "authorities": {}
//     }
    
    @CrossOrigin
    @RequestMapping(value = "/user/editor/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User newUserEditor(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/colaborador/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User newUserColaborador(@Valid @RequestBody User user) {
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        Date date = new Date();
        
        user.setLastPasswordResetDate(date);
        return userRepository.save(user);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/encargado/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User newUserEncargado(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/administrador/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User newUserAdministrador(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    
    
    //NO BORRAR, PRIVATIZA LA VISIÓN DE LOS USUARIOS
    @CrossOrigin
    @RequestMapping(value = "/users/**", method = GET)
    public String users() {
        return "Privado";
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/nexo/", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void generarNexo() {
        int i = 1;
        int e = 2;
        userRepository.nexo(i, e);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/user/editarNexo/{id}", method = PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public User editarNexo(@Valid @PathVariable long id, @RequestBody User user) {
        /*
           User usuario = userRepository.findByUsername(user.getUsername());
           user.setPassword(usuario.getPassword());
           System.out.println(user.getUsername());
           //User usrObj = (User) userDetailsService.loadUserByUsername(user.getUsername());
           Date date = new Date();
        
           user.setLastPasswordResetDate(date);
           user.setId(id);
        */ 
        try {
            user.setId(id);
        Date date = new Date();
        user.setLastPasswordResetDate(date);
        return userRepository.save(user);
            
        } catch (Exception e) {
            User User = null;
            return User;
        }
        
    }
    
    /*
    @CrossOrigin
    @RequestMapping(value = "/user/nuevo/", method = GET)
    @ResponseStatus(HttpStatus.CREATED)
    public User traerUsers() {
        
    }
    
    OBJETO PARA CREAR NUEVO USUARIO CON ROLES A LA VEZ
    {
    "username": "admineeesdfafasdeeee",
    "password": "asdsdddddd",
	"lastPasswordResetDate": "",
    "enabled": true,
    "authorities": [{"id" : 1}]
    }
    
    
    OBJETO PARA EDITAR EL USUARIO
    
    {
	
    "username": "admin",
    "authorities": [{"id" : 1}],
    "enabled": true,
    "lastPasswordResetDate": "",
    "password" : "aaaaaaaaaaaa"
}
*/
}

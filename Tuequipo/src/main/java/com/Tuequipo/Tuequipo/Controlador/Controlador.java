
package com.Tuequipo.Tuequipo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class Controlador {
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,@RequestParam(required = false) String logout, ModelMap model){
        if(error != null){
            model.put("error", "Nombre de Equipo incorrecto");
        }
//        if(logout != null){
//            model.put("logout", "Ha salido correctamente del sitio");
//        }
        return "login.html";
    }
    
}
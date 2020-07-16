
package com.Tuequipo.Tuequipo.Controlador;

import com.Tuequipo.Tuequipo.Enumeracion.CantidadJugadores;
import com.Tuequipo.Tuequipo.Enumeracion.Categoria;
import com.Tuequipo.Tuequipo.Enumeracion.Dias;
import com.Tuequipo.Tuequipo.Enumeracion.Turno;
import com.Tuequipo.Tuequipo.Enumeracion.Zonas;
import com.Tuequipo.Tuequipo.Errores.ErrorServicio;
import com.Tuequipo.Tuequipo.enumeracion.Tipo;
import com.Tuequipo.Tuequipo.servicios.EquipoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller

@RequestMapping("/")
public class Controlador {
@Autowired
EquipoServicio equipoServicio;

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/log")
    public String cargaEquipo(ModelMap modelo,MultipartFile archivo, @RequestParam(required = true) String nombre, @RequestParam(required = true) String mail, @RequestParam(required = true) String descripcion, @RequestParam(required = true) String clave1, @RequestParam(required = true) String clave2, @RequestParam(required = true) String telefono1, @RequestParam(required = true) String telefono2, @RequestParam(required = true) Turno turno, @RequestParam(required = true) Zonas zona, @RequestParam Dias dia, @RequestParam(required = true) Tipo tipo, @RequestParam(required = true) Categoria categoria, @RequestParam(required = true) CantidadJugadores cantidadJugadores){
        try {
            equipoServicio.cargaEquipo(archivo, nombre, mail, descripcion, clave1, clave2, telefono1, telefono2, turno, zona, dia, tipo, categoria, cantidadJugadores);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("mail", mail);
            modelo.put("descripcion", descripcion);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave2);
            modelo.put("telefono1", telefono1);
            modelo.put("telefono2", telefono2);
            modelo.put("turno", turno);
            modelo.put("zona", zona);
            modelo.put("dia", dia);
            modelo.put("tipo", tipo);
            modelo.put("categoria", categoria);
            modelo.put("cantidadJugadores", cantidadJugadores);
            modelo.put("archivo", archivo);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            return "registro.html";
        }
        return "exito.html";
}
}
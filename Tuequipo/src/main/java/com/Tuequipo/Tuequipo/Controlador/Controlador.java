
package com.Tuequipo.Tuequipo.Controlador;

import com.Tuequipo.Tuequipo.Enumeracion.CantidadJugadores;
import com.Tuequipo.Tuequipo.Enumeracion.Categoria;
import com.Tuequipo.Tuequipo.Enumeracion.Dias;
import com.Tuequipo.Tuequipo.Enumeracion.Turno;
import com.Tuequipo.Tuequipo.Enumeracion.Zonas;
import com.Tuequipo.Tuequipo.Errores.ErrorServicio;
import com.Tuequipo.Tuequipo.Repositorios.FotoRepositorio;
import com.Tuequipo.Tuequipo.Servicios.BuscadorServicio;
import com.Tuequipo.Tuequipo.entidades.Equipo;
import com.Tuequipo.Tuequipo.entidades.Foto;
import com.Tuequipo.Tuequipo.enumeracion.Tipo;
import com.Tuequipo.Tuequipo.servicios.EquipoServicio;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/")
public class Controlador {
    @Autowired
    private EquipoServicio equipoServicio;

    @Autowired
    private BuscadorServicio buscadorServicio;
    
    @Autowired
    private FotoRepositorio fotoRepositorio;

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/Registro")
    public String registro(){
        return "Registro.html";
    }
    
    @GetMapping("/Perfil")
    public String miPerfil(){
        return "perfil.html";
    }
    
    @PostMapping("/registrar")
    public String cargaEquipo(ModelMap modelo,MultipartFile archivo, @RequestParam(required = true) String nombre, @RequestParam String mail, @RequestParam String descripcion, @RequestParam(required = true) String clave1, @RequestParam(required = true) String clave2, @RequestParam String telefono1, @RequestParam String telefono2, @RequestParam Turno turno, @RequestParam Zonas zona, @RequestParam Dias dia, @RequestParam Tipo tipo, @RequestParam Categoria categoria, @RequestParam CantidadJugadores cantidadJugadores){
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
            return "Registro.html";
        }
        return "RegistroExitoso.html";
}
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,@RequestParam(required = false) String logout, ModelMap model){
        if(error != null){
            model.put("error", "Usuario o clave incorrectos");
        }
        if(logout != null){
            model.put("logout", "Ha salido correctamente");
        }
        return "login.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/buscador")
    public String buscador(){
        return "buscador.html";
     }
     
    @PostMapping("/buscar")
    public String buscar(ModelMap modelo, @RequestParam String turno, @RequestParam String zona, @RequestParam String dia, @RequestParam String tipo, @RequestParam String categoria, @RequestParam String cantidadJugadores){
        HashSet<Equipo> equipos = buscadorServicio.buscarEquipos(zona, categoria, cantidadJugadores, turno, tipo);
        modelo.put("equipos", equipos);
        return "resultado.html";
    }
    
    @GetMapping("/cargar/{id}")
    public ResponseEntity<byte[]> cargarfoto(@PathVariable String id){
        Foto foto = fotoRepositorio.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
    }

}
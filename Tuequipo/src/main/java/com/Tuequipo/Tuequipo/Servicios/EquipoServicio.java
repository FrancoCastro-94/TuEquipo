package com.Tuequipo.Tuequipo.servicios;
import com.Tuequipo.Tuequipo.Servicios.FotoServicio;
import com.Tuequipo.Tuequipo.Enumeracion.CantidadJugadores;
import com.Tuequipo.Tuequipo.Enumeracion.Categoria;
import com.Tuequipo.Tuequipo.Enumeracion.Dias;
import com.Tuequipo.Tuequipo.Enumeracion.Turno;
import com.Tuequipo.Tuequipo.Enumeracion.Zonas;
import com.Tuequipo.Tuequipo.Errores.ErrorServicio;
import com.Tuequipo.Tuequipo.Repositorios.EquipoRepositorio;
import com.Tuequipo.Tuequipo.entidades.Equipo;
import com.Tuequipo.Tuequipo.entidades.Foto;
import com.Tuequipo.Tuequipo.enumeracion.Tipo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EquipoServicio implements UserDetailsService {

    @Autowired
    private EquipoRepositorio equipoRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void cargaEquipo(MultipartFile archivo, String nombre, String mail, String descripcion, String clave1, String clave2, String telefono1, String telefono2, Turno turno, Zonas zona, Dias dia, Tipo tipo, Categoria categoria, CantidadJugadores cantidadJugadores) throws ErrorServicio {
        validar(nombre, mail, clave1, clave2, telefono1, telefono2, turno, zona, dia, tipo, categoria, cantidadJugadores);
        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setMail(mail);
        equipo.setDescripcion(descripcion);
        String encriptada = new BCryptPasswordEncoder().encode(clave1);
        equipo.setClave(encriptada);
        equipo.setNumero1(telefono1);
        equipo.setNumero2(telefono2);
        equipo.setTurno(turno);
        equipo.setZona(zona);
        equipo.setDia(dia);
        equipo.setTipo(tipo);
        equipo.setCategoria(categoria);
        equipo.setCantidadJugadores(cantidadJugadores);
        equipo.setDisponible(Boolean.TRUE);
        equipo.setAlta(new Date());
        Foto foto = fotoServicio.guardar(archivo);
        equipo.setFoto(foto);

        equipoRepositorio.save(equipo);
    }

    @Transactional
    public void modificarEquipo(MultipartFile archivo, String nombre, String mail, String descripcion, String clave1, String clave2, String telefono1, String telefono2, Turno turno, Zonas zona, Dias dia, Tipo tipo, Categoria categoria, CantidadJugadores cantidadJugadores, Boolean disponible) throws ErrorServicio {
        validar(nombre, mail, clave1, clave2, telefono1, telefono2, turno, zona, dia, tipo, categoria, cantidadJugadores);
        Optional<Equipo> respuesta = equipoRepositorio.findById(nombre);
        if (respuesta.isPresent()) {
            Equipo equipo = respuesta.get();
            equipo.setNombre(nombre);
            equipo.setMail(mail);
            equipo.setDescripcion(descripcion);
            String encriptada = new BCryptPasswordEncoder().encode(clave1);
            equipo.setClave(encriptada);
            equipo.setNumero1(telefono1);
            equipo.setNumero2(telefono2);
            equipo.setTurno(turno);
            equipo.setZona(zona);
            equipo.setDia(dia);
            equipo.setTipo(tipo);
            equipo.setCategoria(categoria);
            equipo.setCantidadJugadores(cantidadJugadores);
            equipo.setDisponible(disponible);
            String idFoto = null;
            if (equipo.getFoto() != null) {
                idFoto = equipo.getFoto().getId();
            }
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            equipo.setFoto(foto);

            equipoRepositorio.save(equipo);
        } else {
            throw new ErrorServicio("No se encontro el equipo solicitado");
        }
    }

    @Transactional
    public void habilitar(String nombre) throws ErrorServicio {

        Optional<Equipo> respuesta = equipoRepositorio.findById(nombre);

        if (respuesta.isPresent()) {

            Equipo equipo = respuesta.get();
            equipo.setBaja(null);
            equipo.setDisponible(Boolean.TRUE);
            equipoRepositorio.save(equipo);

        } else {
            throw new ErrorServicio("No se encontro el equipo solicitado");
        }
    }

    public Equipo buscarPorId(String nombre) {
        Optional<Equipo> respuesta = equipoRepositorio.findById(nombre);
        return respuesta.get();
    }

    @Transactional
    public void bajaEquipo(String nombre) throws ErrorServicio {
        Optional<Equipo> respuesta = equipoRepositorio.findById(nombre);
        if (respuesta.isPresent()) {
            Equipo equipo = respuesta.get();
            equipo.setBaja(new Date());
            equipo.setDisponible(Boolean.FALSE);
            equipoRepositorio.save(equipo);
        }
    }

    private void validar(String nombre, String mail, String clave1, String clave2, String telefono1, String telefono2, Turno turno, Zonas zona, Dias dia, Tipo tipo, Categoria categoria, CantidadJugadores cantidadJugadores) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del equipo no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo");
        }
        if (clave1 == null || clave1.isEmpty() || clave1.length() <= 6) {
            throw new ErrorServicio("La clave debe tener más de 6 caracteres");
        }
        if (!clave2.equals(clave1)) {
            throw new ErrorServicio("Las claves deben coincidir");
        }
        if (telefono1 == null || telefono1.isEmpty()) {
            throw new ErrorServicio("El telefono del equipo no puede ser nulo");
        }
        if (telefono2 == null || telefono2.isEmpty()) {
            throw new ErrorServicio("El telefono del equipo no puede ser nulo");
        }
        if (turno == null) {
            throw new ErrorServicio("Debes elegir el turno para jugar");
        }
        if (zona == null) {
            throw new ErrorServicio("Debes elegir en que zona queres jugar");
        }
        if (dia == null) {
            throw new ErrorServicio("Debes elegir que dia queres jugar");
        }
        if (tipo == null) {
            throw new ErrorServicio("Debes seleccionar que tipo de equipo jugará");
        }
        if (categoria == null) {
            throw new ErrorServicio("Debes elegir la categoria de tu equipo");
        }
        if (cantidadJugadores == null) {
            throw new ErrorServicio("Debes seleccionar que cantidad de jugadores jugarán");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Equipo equipo = equipoRepositorio.buscarEquipoPorNombre(nombre);
        if (equipo != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLES_USUARIO_REGISTRADO");
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", equipo);

            User user = new User(equipo.getNombre(), equipo.getClave(), permisos);
            return user;

        } else {
            return null;
        }
    }

}

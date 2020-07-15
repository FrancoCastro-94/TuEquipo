
package com.Tuequipo.Tuequipo.servicios;

import com.Tuequipo.Tuequipo.Repositorios.EquipoRepositorio;
import com.Tuequipo.Tuequipo.entidades.Equipo;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class EquipoServicio implements UserDetailsService {

    @Autowired
    private EquipoRepositorio equipoRepositorio;
    
    
    
    
    
    @Override  
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException{
        Equipo equipo = equipoRepositorio.buscarEquipoPorNombre(nombre);
        if (equipo != null) {
            
            List<GrantedAuthority> permisos = new ArrayList<>();
                    
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLES_USUARIO_REGISTRADO");
            permisos.add(p1);
            
            ServletRequestAttributes attr =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", equipo);
            
            User user = new User(equipo.getNombre() , equipo.getClave() , permisos);
            return user;
            
        }else{
            return null;
        }
    }
    
    
}

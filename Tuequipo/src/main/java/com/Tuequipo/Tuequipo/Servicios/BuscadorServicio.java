package com.Tuequipo.Tuequipo.Servicios;

import com.Tuequipo.Tuequipo.Repositorios.EquipoRepositorio;
import com.Tuequipo.Tuequipo.entidades.Equipo;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscadorServicio {
    
    @Autowired
    private EquipoRepositorio equipoRepositorio;
    
    public HashSet<Equipo> buscarEquipos(String zona, String categoria, String cantidadJugadores, String turno, String tipo){
        
        HashSet<Equipo> listaEquipos = new HashSet();
        listaEquipos.addAll(equipoRepositorio.buscarEquipoPorZona(zona));
//        listaEquipos.addAll(equipoRepositorio.buscarEquipoPorCategoria(categoria));
//        listaEquipos.addAll(equipoRepositorio.buscarEquipoTurno(turno));
//        listaEquipos.addAll(equipoRepositorio.buscarEquipoPorZona(cantidadJugadores));
        return listaEquipos;
        
    }
    
}

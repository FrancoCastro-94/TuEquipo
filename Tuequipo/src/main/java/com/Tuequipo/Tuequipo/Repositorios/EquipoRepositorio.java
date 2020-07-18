
package com.Tuequipo.Tuequipo.Repositorios;

import com.Tuequipo.Tuequipo.entidades.Equipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipoRepositorio extends JpaRepository <Equipo,String>{
 
    @Query ("SELECT c FROM Equipo c  WHERE c.nombre = :nombre")
    public Equipo buscarEquipoPorNombre(@Param("nombre") String nombre);
    
    @Query ("SELECT c FROM Equipo c WHERE c.mail= :mail")
    public Equipo buscarEquipoPorMail(@Param("mail") String mail);
    
    @Query ("SELECT c FROM Equipo c WHERE c.zona= :zona AND c.disponible= 1")
    public List<Equipo> buscarEquipoPorZona(@Param("zona") String zona);
    
    @Query ("SELECT c FROM Equipo c WHERE c.categoria= :categoria AND c.disponible= 1")
    public List<Equipo> buscarEquipoPorCategoria(@Param("categoria") String categoria);
    
    @Query ("SELECT c FROM Equipo c WHERE c.cantidadJugadores= :cantidadJugadores AND c.disponible= 1")
    public List<Equipo> buscarEquipoCantidadJugadores(@Param("cantidadJugadores") String cantidadJugadores);
    
    @Query ("SELECT c FROM Equipo c WHERE c.turno= :turno AND c.disponible= 1")
    public List<Equipo> buscarEquipoTurno(@Param("turno") String turno);

    @Query ("SELECT c FROM Equipo c WHERE c.tipo= :tipo AND c.disponible= 1")
    public List<Equipo> buscarEquipoTipo(@Param("tipo") String tipo);
    
}

package com.Tuequipo.Tuequipo.entidades;

import com.Tuequipo.Tuequipo.Enumeracion.CantidadJugadores;
import com.Tuequipo.Tuequipo.Enumeracion.Categoria;
import com.Tuequipo.Tuequipo.Enumeracion.Dias;
import com.Tuequipo.Tuequipo.Enumeracion.Turno;
import com.Tuequipo.Tuequipo.Enumeracion.Zonas;
import com.Tuequipo.Tuequipo.enumeracion.Tipo;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Equipo {
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid")
    private String id;
    
    private String nombre;
    private String clave;
    private String mail;
    private String numero1;
    private String numero2;
    private String descripcion;
    
    @OneToOne
    private Foto foto;
    
    private List<Turno> turnos;
    private Tipo tipo;
    private List<Zonas> zonas;
    private List<Dias> dias;
    private Categoria categoria;
    private CantidadJugadores cantidadJugadores;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the numero1
     */
    public String getNumero1() {
        return numero1;
    }

    /**
     * @param numero1 the numero1 to set
     */
    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    /**
     * @return the numero2
     */
    public String getNumero2() {
        return numero2;
    }

    /**
     * @param numero2 the numero2 to set
     */
    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the foto
     */
    public Foto getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    /**
     * @return the turnos
     */
    public List<Turno> getTurnos() {
        return turnos;
    }

    /**
     * @param turnos the turnos to set
     */
    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the zonas
     */
    public List<Zonas> getZonas() {
        return zonas;
    }

    /**
     * @param zonas the zonas to set
     */
    public void setZonas(List<Zonas> zonas) {
        this.zonas = zonas;
    }

    /**
     * @return the dias
     */
    public List<Dias> getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(List<Dias> dias) {
        this.dias = dias;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the cantidadJugadores
     */
    public CantidadJugadores getCantidadJugadores() {
        return cantidadJugadores;
    }

    /**
     * @param cantidadJugadores the cantidadJugadores to set
     */
    public void setCantidadJugadores(CantidadJugadores cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    /**
     * @return the alta
     */
    public Date getAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Date alta) {
        this.alta = alta;
    }

    /**
     * @return the baja
     */
    public Date getBaja() {
        return baja;
    }

    /**
     * @param baja the baja to set
     */
    public void setBaja(Date baja) {
        this.baja = baja;
    }
    
    
    
}

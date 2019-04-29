/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identificador")
    private Integer identificador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "responsable", referencedColumnName = "identificador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Responsables responsable;

    public Actividades() {
    }

    public Actividades(Integer identificador) {
        this.identificador = identificador;
    }

    public Actividades(Integer identificador, Date fechaCreacion, Date fechaLimite, String estado) {
        this.identificador = identificador;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Responsables getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsables responsable) {
        this.responsable = responsable;
    }

    @Override
    public int hashCode() {
        int hash = 7 * 31 + (identificador != null ? identificador.hashCode() : 0);
        hash *= 31 + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        hash *= 31 + (fechaLimite != null ? fechaLimite.hashCode() : 0);
        hash *= 31 + (responsable != null ? responsable.hashCode() : 0);
        hash *= 31 + (estado != null ? estado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if (!this.fechaCreacion.equals(other.fechaCreacion)) {
            return false;
        } else if (!this.fechaLimite.equals(other.fechaLimite)) {
            return false;
        } else if (!this.responsable.equals(other.responsable)) {
            return false;
        } else if ((this.descripcion == null && other.descripcion != null) || (this.descripcion != null && !this.descripcion.equals(other.descripcion))) {
            return false;
        } else if (!this.estado.equals(other.estado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Actividades[ identificador=" + identificador + " ]";
    }

}

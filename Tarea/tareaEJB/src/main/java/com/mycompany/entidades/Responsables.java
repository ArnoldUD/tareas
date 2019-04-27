/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ArnoldUD
 */
@Entity
@Table(name = "RESPONSABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsables.findAll", query = "SELECT r FROM Responsables r")})
public class Responsables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable", fetch = FetchType.LAZY)
    private List<Actividades> actividadesList;

    public Responsables() {
    }

    public Responsables(Integer identificador) {
        this.identificador = identificador;
    }

    public Responsables(Integer identificador, String nombres, String apellidos) {
        this.identificador = identificador;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsables)) {
            return false;
        }
        Responsables other = (Responsables) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Responsables[ identificador=" + identificador + " ]";
    }
    
}

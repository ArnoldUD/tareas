/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.responsables;

import com.mycompany.controlador.ControladorBean;
import com.mycompany.entidades.Responsables;
import com.mycompany.transacciones.ResponsableFacade;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author usuario
 */
@Named(value = "responsablesManagedBean")
@RequestScoped
public class ResponsablesManagedBean extends ControladorBean {

    @EJB
    protected ResponsableFacade responsableFacade;

    private Responsables responsables;
    private Responsables responsablesActual;
    private List<Responsables> responsablesList;
    private boolean mostrarFormulario;
    private boolean mostrarTabla;
    private boolean deshabilitarBoton;

    @Override
    @PostConstruct
    public void constructor() {
        destructor();
        responsablesList = responsableFacade.findAll();
    }

    @Override
    @PreDestroy
    public void destructor() {
        responsables = new Responsables();
        responsablesActual = new Responsables();
        if (responsablesList != null
                && !responsablesList.isEmpty()) {
            responsablesList.clear();
        }
        responsablesList = null;
        mostrarFormulario = false;
        mostrarTabla = true;
        deshabilitarBoton = true;
    }

    public void eventoCargarEntidadActualListener() {
        cargarEntidadActual();
        mostrarFormulario = true;
        mostrarTabla = false;
    }

    public void eventoDescargarEntidadActualListener() {
        descargarEntidadActual();
        mostrarFormulario = false;
        mostrarTabla = true;
    }

    public void eventDetectarCambiosListener() {
        detectarCambios();
    }

    public void accionVolverListener() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect("responsableEditar.xhtml");
            if (!deshabilitarBoton) {
                eventoDescargarEntidadActualListener();
            }
        } catch (IOException ex) {
            Logger.getLogger(ResponsablesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarEntidadActual() {
        if (responsables != null
                && responsables.getIdentificador() != null) {
            responsablesActual = new Responsables(responsables.getIdentificador());
            responsablesActual.setActividadesList(responsables.getActividadesList());
            responsablesActual.setApellidos(responsables.getApellidos());
            responsablesActual.setEmail(responsables.getEmail());
            responsablesActual.setNombres(responsables.getNombres());
            responsablesActual.setTelefono(responsables.getTelefono());
        }
    }

    private void descargarEntidadActual() {
        if (responsablesActual != null
                && responsablesActual.getIdentificador() != null) {
            responsables.setIdentificador(responsablesActual.getIdentificador());
            responsables.setActividadesList(responsablesActual.getActividadesList());
            responsables.setApellidos(responsablesActual.getApellidos());
            responsables.setEmail(responsablesActual.getEmail());
            responsables.setNombres(responsablesActual.getNombres());
            responsables.setTelefono(responsablesActual.getTelefono());
        }
    }

    private void detectarCambios() {
        if (responsables != null && responsables.getIdentificador() != null) {
            deshabilitarBoton = responsables.equals(responsablesActual);
        }
    }

    public Responsables getResponsables() {
        return responsables;
    }

    public void setResponsables(Responsables responsables) {
        this.responsables = responsables;
    }

    public List<Responsables> getResponsablesList() {
        return responsablesList;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public boolean isDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(boolean deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }
}

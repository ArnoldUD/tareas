/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividades;

import com.mycompany.controlador.ControladorBean;
import com.mycompany.entidades.Actividades;
import com.mycompany.entidades.Responsables;
import com.mycompany.enums.EstadoActividadEnum;
import com.mycompany.transacciones.ActividadFacade;
import com.mycompany.transacciones.ResponsableFacade;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author ArnoldUD
 */
@Named(value = "actividadesManagedBean")
@RequestScoped
public class ActividadesManagedBean extends ControladorBean {

    @EJB
    protected ActividadFacade actividadFacade;
    @EJB
    protected ResponsableFacade responsableFacade;

    private List<Actividades> actividadesList;
    private List<String> estadoActividadEnumList;
    private Actividades actividades;
    private Actividades actividadesActual;
    private boolean mostrarFormulario;
    private boolean mostrarTabla;
    private boolean deshabilitarSeleccion;
    private boolean deshabilitarBoton;

    @Override
    @PostConstruct
    public void constructor() {
        destructor();
        actividadesList = actividadFacade.findAll();
    }

    @Override
    @PreDestroy
    public void destructor() {
        actividades = new Actividades();
        actividadesActual = new Actividades();
        if (actividadesList != null
                && !actividadesList.isEmpty()) {
            actividadesList.clear();
        }
        actividadesList = null;
        mostrarFormulario = false;
        mostrarTabla = true;
        deshabilitarSeleccion = true;
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
            facesContext.getExternalContext().redirect("actividadEditar.xhtml");
            if (!deshabilitarBoton) {
                eventoDescargarEntidadActualListener();
            }
        } catch (IOException ex) {
            Logger.getLogger(ActividadesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarEntidadActual() {
        if (actividades != null
                && actividades.getIdentificador() != null) {
            actividadesActual = new Actividades(actividades.getIdentificador());
            actividadesActual.setDescripcion(actividades.getDescripcion());
            actividadesActual.setEstado(actividades.getEstado());
            actividadesActual.setFechaCreacion(actividades.getFechaCreacion());
            actividadesActual.setFechaLimite(actividades.getFechaLimite());
            actividadesActual.setResponsable(actividades.getResponsable());
        }
    }

    private void descargarEntidadActual() {
        if (actividadesActual != null
                && actividadesActual.getIdentificador() != null) {
            actividades.setIdentificador(actividadesActual.getIdentificador());
            actividades.setDescripcion(actividadesActual.getDescripcion());
            actividades.setEstado(actividadesActual.getEstado());
            actividades.setFechaCreacion(actividadesActual.getFechaCreacion());
            actividades.setFechaLimite(actividadesActual.getFechaLimite());
            actividades.setResponsable(actividadesActual.getResponsable());
        }
    }

    private void detectarCambios() {
        if (actividades != null && actividades.getIdentificador() != null) {
            deshabilitarBoton = actividades.equals(actividadesActual);
        }
    }

    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public List<String> getEstadoActividadEnumList() {
        if (estadoActividadEnumList == null) {
            estadoActividadEnumList = new ArrayList<>();
            for (EstadoActividadEnum estadoActividadEnum : EstadoActividadEnum.values()) {
                estadoActividadEnumList.add(estadoActividadEnum.name());
            }
        }
        return estadoActividadEnumList;
    }

    public List<Responsables> getResponsablesList() {
        return responsableFacade.findAll();
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
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

    public boolean isDeshabilitarSeleccion() {
        return deshabilitarSeleccion;
    }

    public void setDeshabilitarSeleccion(boolean deshabilitarSeleccion) {
        this.deshabilitarSeleccion = deshabilitarSeleccion;
    }

    public boolean isDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(boolean deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }
}

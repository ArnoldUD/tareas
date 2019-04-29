/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividades;

import com.mycompany.entidades.Responsables;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.PersistenceException;

/**
 *
 * @author ArnoldUD
 */
@Named(value = "actividadesEliminarBean")
@ViewScoped
public class ActividadesEliminarBean extends ActividadesManagedBean {

    @Override
    @PostConstruct
    public void constructor() {
        super.constructor(); //To change body of generated methods, choose Tools | Templates.
        setDeshabilitarSeleccion(false);
    }

    public void btnEliminarActividadListener() {
        try {
            eliminarActividad();
            infoMensaje("Se ha eliminado el registro id={0}", getActividades().getIdentificador());
            constructor();
        } catch (PersistenceException e) {
            errorMensaje(e);
        }
    }

    private void eliminarActividad() throws PersistenceException {
        if (getActividades() != null
                && getActividades().getIdentificador() != null) {
            Responsables responsables = getActividades().getResponsable();
            responsables.getActividadesList().remove(getActividades());
            responsableFacade.edit(responsables);
            actividadFacade.remove(getActividades());
        }
    }
}

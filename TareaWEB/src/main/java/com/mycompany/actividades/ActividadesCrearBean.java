/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividades;

import com.mycompany.entidades.Responsables;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.PersistenceException;

/**
 *
 * @author usuario
 */
@Named(value = "actividadesCrearBean")
@ViewScoped
public class ActividadesCrearBean extends ActividadesManagedBean {

    public void eventoCrearActividadListener() {
        try {
            crearActividad();
            infoMensaje("Se almacenó correctamente", (Object[]) null);
        } catch (PersistenceException e) {
            errorMensaje(e);
        }
    }

    private void crearActividad() throws PersistenceException {
        if (getActividades() != null
                && getActividades().getResponsable() != null) {
            Responsables responsables = getActividades().getResponsable();
            responsables.getActividadesList().add(getActividades());
            responsableFacade.edit(responsables);
            constructor();
        }
    }
}

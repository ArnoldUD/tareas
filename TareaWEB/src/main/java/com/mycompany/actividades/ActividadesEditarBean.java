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
@Named(value = "actividadesEditarBean")
@ViewScoped
public class ActividadesEditarBean extends ActividadesManagedBean {

    @Override
    @PostConstruct
    public void constructor() {
        super.constructor(); //To change body of generated methods, choose Tools | Templates.
        setDeshabilitarSeleccion(false);
    }

    public void accionEditarActividadListener() {
        try {
            editarActividad();
            constructor();
            infoMensaje("Se guardaron los cambios", (Object[]) null);
        } catch (PersistenceException e) {
            errorMensaje(e);
        }
    }

    private void editarActividad() throws PersistenceException {
        Responsables responsables = getActividades().getResponsable();
        responsables.getActividadesList().add(getActividades());
        responsableFacade.edit(responsables);
    }
}

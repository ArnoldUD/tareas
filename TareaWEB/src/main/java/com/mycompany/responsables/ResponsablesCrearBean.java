/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.responsables;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.PersistenceException;

/**
 *
 * @author usuario
 */
@Named(value = "responsablesCrearBean")
@ViewScoped
public class ResponsablesCrearBean extends ResponsablesManagedBean {

    public void accionCrearResponsableListener() {
        try {
            crearResponsable();
            infoMensaje("Se ha registrado exitosamente", (Object[]) null);
        } catch (PersistenceException e) {
            errorMensaje(e);
        }
    }

    private void crearResponsable() throws PersistenceException {
        responsableFacade.create(getResponsables());
        destructor();
    }
}

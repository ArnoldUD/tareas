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
@Named(value = "responsablesEditarBean")
@ViewScoped
public class ResponsablesEditarBean extends ResponsablesManagedBean {

    public void accionEditarResponsableListener() {
        try {
            editarResponsable();
            constructor();
            infoMensaje("Se guardaron los cambios", (Object[]) null);
        } catch (PersistenceException e) {
            errorMensaje(e);
        }
    }

    private void editarResponsable() throws PersistenceException {
        responsableFacade.edit(getResponsables());
    }
}

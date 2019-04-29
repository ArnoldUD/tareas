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
@Named(value = "responsablesEliminarBean")
@ViewScoped
public class ResponsablesEliminarBean extends ResponsablesManagedBean {

    public void btnEliminarResponsableListener() {
        try {
            eliminarResponsable();
            infoMensaje("Se ha eliminado el registro id={0}", getResponsables().getIdentificador());
            constructor();
        } catch (PersistenceException e) {
            errorMensaje(e);
        }
    }

    private void eliminarResponsable() throws PersistenceException {
        if (getResponsables() != null
                && getResponsables().getIdentificador() != null) {
            if (getResponsables().getActividadesList() != null
                    && !getResponsables().getActividadesList().isEmpty()) {
                throw new PersistenceException("No se puede eliminar por estar asociado a una o varias actividades");
            }
            responsableFacade.remove(getResponsables());
        }
    }
}

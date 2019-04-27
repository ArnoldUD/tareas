/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.transacciones;

import com.mycompany.entidades.Actividades;
import com.mycompany.facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ArnoldUD
 */
@Stateless
public class ActividadFacade extends AbstractFacade<Actividades> {

    @PersistenceContext(unitName = "tareaUP")
    private EntityManager em;

    public ActividadFacade() {
        super(Actividades.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = AbstractFacade.ENTITY_MANAGER_FACTORY.createEntityManager();
        }
        return em;
    }
}

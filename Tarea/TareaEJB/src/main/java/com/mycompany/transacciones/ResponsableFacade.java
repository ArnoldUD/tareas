/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.transacciones;

import com.mycompany.entidades.Responsables;
import com.mycompany.facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ArnoldUD
 */
@Stateless
public class ResponsableFacade extends AbstractFacade<Responsables> {

    @PersistenceContext(unitName = "tareaUP")
    private EntityManager em;
    
    public ResponsableFacade() {
        super(Responsables.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if(em == null) {
            em = AbstractFacade.ENTITY_MANAGER_FACTORY.createEntityManager();
        }
        return em;
    }
}

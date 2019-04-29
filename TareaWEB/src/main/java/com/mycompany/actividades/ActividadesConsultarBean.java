/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividades;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ArnoldUD
 */
@Named(value = "actividadesConsultarBean")
@ViewScoped
public class ActividadesConsultarBean extends ActividadesManagedBean {

    @Override
    @PostConstruct
    public void constructor() {
        super.constructor(); //To change body of generated methods, choose Tools | Templates.
        setDeshabilitarSeleccion(true);
    }
    
}

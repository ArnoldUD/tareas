/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controlador;

import java.io.Serializable;
import java.util.MissingResourceException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ArnoldUD
 */
@Local
public abstract class ControladorBean implements Serializable {

    /**
     * Método constructor para inicializar campos
     */
    @PostConstruct
    abstract public void constructor();

    /**
     * Método destructor para limpiar campos
     */
    @PreDestroy
    abstract public void destructor();

    /**
     * Método que permite mostrar mensajes informativos al usuario
     *
     * @param clave Este parámetro permite indicar la clave-valor del dicho
     * mensaje de un archivo de propiedades del sistema
     * @param infoAdicional Este parámetro permite contener información
     * adicional al mensaje
     */
    protected static void infoMensaje(String clave, Object... infoAdicional) {
        try {
            String mensaje = clave;//obtenerMensaje(clave);
            String patron;
            if (infoAdicional != null
                    && infoAdicional.length > 0
                    && infoAdicional[0] != null) {
                for (int i = 0; i < infoAdicional.length; i++) {
                    patron = "{" + i + "}";
                    mensaje = mensaje.replace(patron, infoAdicional[i].toString());
                }
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                    "INFORMACIÓN",
                                    mensaje));
        } catch (MissingResourceException ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "ERROR",
                                    ex.getMessage()));
        }
    }

    /**
     * Método para mostrar advertencia o precauciones del sistema generado por
     * errores manejables de usuario
     *
     * @param exception Este parámetro contiene las causas que generaron dicha
     * advertencia en el sistema
     * @param infoAdicional Este parámetro permite contener información
     * adicional al mensaje
     */
    protected static void precaucionMensaje(Exception exception, Object... infoAdicional) {
        String mensaje = exception.getMessage();
        String patron;
        if (infoAdicional != null
                && infoAdicional.length > 0
                && infoAdicional[0] != null) {
            for (int i = 0; i < infoAdicional.length; i++) {
                patron = "{" + i + "}";
                mensaje = mensaje.replace(patron, infoAdicional[i].toString());
            }
        }
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                "ADVERTENCIA",
                                mensaje));
    }

    /**
     * Método para mostrar errores no manejables causados por el usuario
     *
     * @param exception Este parámetro contiene las causas que generaron dicho
     * error manejado por el sistema
     */
    protected static void errorMensaje(Exception exception) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "ERROR",
                                exception.getMessage()));
    }

    /**
     * Método que permite mostrar errores fatales o graves generados por el
     * sistema y no estan contemplados por el fabricante del sistema
     *
     * @param exception Este parámetro contiene las causas que generaron dicho
     * error causado por el sistema
     */
    protected static void graveMensaje(Exception exception) {
        try {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_FATAL,
                                    "GRAVE FATAL",
                                    "Grave error!, favor de tomar pantallazo en estos momento y enviarlo a soporte técnico"//obtenerMensaje("msg_grave_error_sistema")
                                    + " "
                                    + exception.getMessage()));
        } catch (MissingResourceException ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "GRAVE FATAL",
                                    ex.getMessage()));
        }
    }
}

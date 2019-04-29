package com.mycompany.converter;

import com.mycompany.entidades.Responsables;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author ArnoldUD
 */
@FacesConverter(value = "responsableConverter")
public class ResponsableConverter implements Converter {
    
    private static List<Responsables> responsablesList;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            if (string != null) {
                Integer identificador = Integer.valueOf(string);
                for (Responsables responsable : responsablesList) {
                    if (responsable.getIdentificador().equals(identificador)) {
                        return responsable;
                    }
                }
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(ResponsableConverter.class.getName()).log(Level.SEVERE, e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            if (o != null) {
                if (o instanceof Responsables) {
                    if (responsablesList == null) {
                        responsablesList = new ArrayList<>();
                    }
                    responsablesList.add((Responsables) o);
                    Integer identificador = ((Responsables) o).getIdentificador();
                    return String.valueOf(identificador);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ResponsableConverter.class.getName()).log(Level.SEVERE, e.getLocalizedMessage());
        }
        return null;
    }
}

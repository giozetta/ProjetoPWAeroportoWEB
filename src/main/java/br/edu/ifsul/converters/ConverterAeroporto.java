/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Aeroporto;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VIP
 */
@Named(value = "converterAeroporto")
@RequestScoped
public class ConverterAeroporto implements Serializable, Converter{

    @PersistenceContext(unitName = "ProjetoPWAeroportoWebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro"))
            return null;
        else
            return em.find(Aeroporto.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if(t == null)
            return null;
        else{
            Aeroporto obj = (Aeroporto) t;
            return obj.getId().toString();
        }
    }
    
}

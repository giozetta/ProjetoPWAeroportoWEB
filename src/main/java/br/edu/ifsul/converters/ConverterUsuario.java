/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Usuario;
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
@Named(value = "converterUsuario")
@RequestScoped
public class ConverterUsuario implements Serializable, Converter {

    @PersistenceContext(unitName = "ProjetoPWAeroportoWebPU")
    private EntityManager em;    
    
    // converter o que vem da tela para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Usuario.class, string);
    }

    // converte o objeto que vem do banco em uma string para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Usuario obj = (Usuario) t;
        return obj.getNome_usuario();
    }
}

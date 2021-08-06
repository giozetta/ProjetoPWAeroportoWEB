/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Classe;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author VIP
 */
@Stateful
public class ClasseDAO<TIPO> extends DAOGenerico<Classe> implements Serializable{
   
    public ClasseDAO(){
        super();
        classePersistente = Classe.class;
    }
    
}

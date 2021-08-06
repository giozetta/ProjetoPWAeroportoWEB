/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aeroporto;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author VIP
 */
@Stateful
public class AeroportoDAO<TIPO> extends DAOGenerico<Aeroporto> implements Serializable{
   
    public AeroportoDAO(){
        super();
        classePersistente = Aeroporto.class;
    }
    
}

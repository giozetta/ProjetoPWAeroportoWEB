/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author VIP
 * @param <TIPO>
 */
@Stateful
public class UsuarioDAO<TIPO>  extends DAOGenerico<Usuario> implements Serializable {
    
    public UsuarioDAO(){
        super();
        classePersistente = Usuario.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("nome_usuario", "Nome Usuario", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);        
                
    }
    
    @Override
    public Usuario localize(Object id) throws Exception {
        Usuario obj = em.find(Usuario.class, id);
        // uso para evitar o erro de lazy inicialization exception
        obj.getPermissoes().size();
        return obj;
    }     
    
    public boolean verificaUnicidadeNomeUsuario(String nome) throws Exception {
        String jpql = "from Usuario where nome_usuario = :pNome";
        Query query = em.createQuery(jpql);
        query.setParameter("pNome", nome);
        if (query.getResultList().size() > 0){
            return false;
        } else {
            return true;
        }
    }    
}
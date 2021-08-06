/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author VIP
 */
@Named(value = "controleCidade")
@ViewScoped
public class ControleCidade implements Serializable{
    @EJB
    private CidadeDAO<Cidade> dao;
    private Cidade objeto;

    public ControleCidade(){
        
    }
    
    public String listar(){
        return "/private/cidade/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Cidade();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.localize(id);
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.localize(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        }catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }
    
    /**
     * @return the dao
     */
    public CidadeDAO<Cidade> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(CidadeDAO<Cidade> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Cidade getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }
    
}

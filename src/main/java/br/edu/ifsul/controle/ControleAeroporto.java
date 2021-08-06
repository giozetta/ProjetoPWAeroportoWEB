/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Aeroporto;
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
@Named(value = "controleAeroporto")
@ViewScoped
public class ControleAeroporto implements Serializable{
    @EJB
    private AeroportoDAO<Aeroporto> dao;
    private Aeroporto objeto;
    
    @EJB
    private CidadeDAO<Cidade> daoCidade; 

    public ControleAeroporto(){
        
    }
    
    public String listar(){
        return "/private/aeroporto/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Aeroporto();
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
    public AeroportoDAO<Aeroporto> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(AeroportoDAO<Aeroporto> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Aeroporto getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Aeroporto objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoCidade
     */
    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    /**
     * @param daoCidade the daoCidade to set
     */
    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }
    
}

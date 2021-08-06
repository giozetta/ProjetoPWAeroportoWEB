/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.modelo.Classe;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author VIP
 */
@Named(value = "controleClasse")
@ViewScoped
public class ControleClasse implements Serializable{
    @EJB
    private ClasseDAO<Classe> dao;
    private Classe objeto;

    public ControleClasse(){
        
    }
    
    public String listar(){
        return "/private/classe/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Classe();
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
    public ClasseDAO<Classe> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ClasseDAO<Classe> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Classe getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Classe objeto) {
        this.objeto = objeto;
    }
    
}

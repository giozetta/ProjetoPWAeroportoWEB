/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Classe;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author VIP
 */
@Named(value = "controleVooAgendado")
@ViewScoped
public class ControleVooAgendado implements Serializable{
    @EJB
    private VooAgendadoDAO<VooAgendado> dao;
    private VooAgendado objeto;
    
    @EJB 
    private PessoaDAO<Pessoa> daoPessoa;
    
    @EJB
    private VooDAO<Voo> daoVoo;
    
    @EJB
    private ClasseDAO<Classe> daoClasse;
    private Passagem passagem;
    private Boolean novaPassagem;

    public ControleVooAgendado(){
        
    }
    
    public void novaPassagem(){
        setNovaPassagem((Boolean) true);
        setPassagem(new Passagem());
    }
    
    public void alterarPassagem(int index){
        setPassagem(getObjeto().getPassagens().get(index));
        setNovaPassagem((Boolean) false);
    }
    
    public void salvarPassagem(){
        if (getNovaPassagem()){
            getObjeto().adicionarPassagem(getPassagem());
        }
        Util.mensagemInformacao("Passagem adicionada ou atualizada com sucesso");
    }
    
    public void removerPassagem(int index){
        getObjeto().removerPassagem(index);
        Util.mensagemInformacao("Passagem removida com sucesso!");
    }

    
    public String listar(){
        return "/private/vooagendado/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new VooAgendado());
    }
    
    public void alterar(Object id){
        try{
            setObjeto(getDao().localize(id));
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try{
            setObjeto(getDao().localize(id));
            getDao().remove(getObjeto());
            Util.mensagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if(getObjeto().getId() == null){
                getDao().persist(getObjeto());
            }else{
                getDao().merge(getObjeto());
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        }catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    /**
     * @return the dao
     */
    public VooAgendadoDAO<VooAgendado> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(VooAgendadoDAO<VooAgendado> dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public VooAgendado getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(VooAgendado objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoPessoa
     */
    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    /**
     * @param daoPessoa the daoPessoa to set
     */
    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    /**
     * @return the daoClasse
     */
    public ClasseDAO<Classe> getDaoClasse() {
        return daoClasse;
    }

    /**
     * @param daoClasse the daoClasse to set
     */
    public void setDaoClasse(ClasseDAO<Classe> daoClasse) {
        this.daoClasse = daoClasse;
    }

    /**
     * @return the passagem
     */
    public Passagem getPassagem() {
        return passagem;
    }

    /**
     * @param passagem the passagem to set
     */
    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    /**
     * @return the novaPassagem
     */
    public Boolean getNovaPassagem() {
        return novaPassagem;
    }

    /**
     * @param novaPassagem the novaPassagem to set
     */
    public void setNovaPassagem(Boolean novaPassagem) {
        this.novaPassagem = novaPassagem;
    }

    /**
     * @return the daoVoo
     */
    public VooDAO<Voo> getDaoVoo() {
        return daoVoo;
    }

    /**
     * @param daoVoo the daoVoo to set
     */
    public void setDaoVoo(VooDAO<Voo> daoVoo) {
        this.daoVoo = daoVoo;
    }
    
}

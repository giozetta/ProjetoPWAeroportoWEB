/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Aeroporto;
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
@Named(value = "controleVoo")
@ViewScoped
public class ControleVoo implements Serializable{
    @EJB
    private VooDAO<Voo> dao;
    private Voo objeto;
    private Boolean novo;
    
    @EJB
    private AeroportoDAO<Aeroporto> daoAeroporto;
    private Aeroporto aeroporto;
    
    private VooAgendado vooAgendado;
    private Boolean novoVooAgendado;
    
    private int abaAtiva;
    
    public ControleVoo(){
        
    }
    
    public void removerAeroporto(Aeroporto obj) {
        objeto.getEscalas().remove(obj);
        Util.mensagemInformacao("Aeroporto removido com sucesso!");
    }
    
    public void adicionarAeroporto() {
        if (!objeto.getEscalas().contains(aeroporto)) {
            if (aeroporto != null) {
                objeto.getEscalas().add(aeroporto);
                Util.mensagemInformacao("Aeroporto adicionado com sucesso!");
            } else {
                Util.mensagemErro("Selecione o aeroporto");
            }
        } else {
            Util.mensagemErro("Esse aeroporto já foi especificado");
        }
    }
    
    public void novoVooAgendado(){
        setNovoVooAgendado((Boolean) true);
        setVooAgendado(new VooAgendado());
    }
    
    public void alterarVooAgendado(int index){
        setVooAgendado(getObjeto().getVoosAgendados().get(index));
        setNovoVooAgendado((Boolean) false);
    }
    
    public void salvarVooAgendado(){
        if(novoVooAgendado){
            objeto.setVooAgendado(vooAgendado);
        }
        Util.mensagemInformacao("Passagem adicionado ou atualizado com sucesso");
    }
    
    public void removerVooAgendado(int index){
        objeto.removerVooAgendado(index);
        Util.mensagemInformacao("Passagem removida com sucesso");
    }
    
    public String listar(){
        return "/private/voo/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Voo();
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
    public VooDAO<Voo> getDao() {
        return dao;
    }
    
    /**
     * @param dao the dao to set
     */
    public void setDao(VooDAO<Voo> dao) {
        this.dao = dao;
    }
    
    /**
     * @return the objeto
     */
    public Voo getObjeto() {
        return objeto;
    }
    
    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Voo objeto) {
        this.objeto = objeto;
    }
    
    /**
     * @return the daoAeroporto
     */
    public AeroportoDAO<Aeroporto> getDaoAeroporto() {
        return daoAeroporto;
    }
    
    /**
     * @param daoAeroporto the daoAeroporto to set
     */
    public void setDaoAeroporto(AeroportoDAO<Aeroporto> daoAeroporto) {
        this.daoAeroporto = daoAeroporto;
    }
    
    /**
     * @return the aeroporto
     */
    public Aeroporto getAeroporto() {
        return aeroporto;
    }
    
    /**
     * @param aeroporto the aeroporto to set
     */
    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
    }
    
    /**
     * @return the vooAgendado
     */
    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }
    
    /**
     * @param vooAgendado the vooAgendado to set
     */
    public void setVooAgendado(VooAgendado vooAgendado) {
        this.vooAgendado = vooAgendado;
    }
    
    /**
     * @return the novoVooAgendado
     */
    public Boolean getNovoVooAgendado() {
        return novoVooAgendado;
    }
    
    /**
     * @param novoVooAgendado the novoVooAgendado to set
     */
    public void setNovoVooAgendado(Boolean novoVooAgendado) {
        this.novoVooAgendado = novoVooAgendado;
    }
    
    /**
     * @return the novo
     */
    public Boolean getNovo() {
        return novo;
    }
    
    /**
     * @param novo the novo to set
     */
    public void setNovo(Boolean novo) {
        this.novo = novo;
    }

    /**
     * @return the abaAtiva
     */
    public int getAbaAtiva() {
        return abaAtiva;
    }

    /**
     * @param abaAtiva the abaAtiva to set
     */
    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }
    
}
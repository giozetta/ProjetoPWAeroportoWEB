/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author VIP
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    private Usuario usuarioAutenticado;
    @EJB
    private UsuarioDAO<Usuario> dao;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
        
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try {
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(usuario, senha);
            if (request.getUserPrincipal() != null){
                System.out.println("Usuario: " + usuario + " senha: " + senha);
                usuarioAutenticado = 
                        dao.localize(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso!");
                usuario = "";
                senha = "";
            }
           return "/index" ;
        } catch (Exception e){
            Util.mensagemErro("Usuário ou senha inválidos: " + Util.getMensagemErro(e));
            return "/login";
        }
    }
    
    public String efetuarLogout(){
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            Util.mensagemInformacao("Logout realizado com sucesso!");
           return "/index" ;
        } catch (Exception e){
            Util.mensagemErro("Erro ao fazer logout: " + Util.getMensagemErro(e));
            return "/index";
        }
    }    

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}

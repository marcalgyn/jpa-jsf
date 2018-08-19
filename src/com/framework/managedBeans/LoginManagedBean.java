package com.framework.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.framework.db.UsuarioDao;
import com.framework.model.Usuario;

@ManagedBean (name = "LoginMB")
@ViewScoped
public class LoginManagedBean {
	
	private UsuarioDao usuarioDAO = new UsuarioDao();
	private Usuario usuario = new Usuario();
	
	public String enviar() {
		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario n�o encontrado!", "Erro no login"));
			return null;
		} else {
			return "/main";
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
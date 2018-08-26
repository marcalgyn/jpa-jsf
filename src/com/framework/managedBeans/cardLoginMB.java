package com.framework.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.framework.db.UsuarioDAO;
import com.framework.model.Usuario;

@ManagedBean (name="cardLoginMB")
@ViewScoped
public class cardLoginMB {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	
	public String enviar(){
		
		apiRecebDadsoCartao();
		
		usuario = usuarioDAO.getUsuarioCard(usuario.getNomeUsuario(), usuario.getMatricula());
		
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entrada Não permitida!", "Erro no login"));
			return null;
		} else {
			return "/validado";
		}
		
	
}
	private void apiRecebDadsoCartao() {
		String nomeUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nomeUsuario");
		String matricula = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("matricula");
		
		usuario.setNomeUsuario(nomeUsuario);
		usuario.setMatricula(matricula);
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

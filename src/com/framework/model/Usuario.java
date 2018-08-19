package com.framework.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "username", nullable = false, unique = true)
	private String nomeUsuario;
	@Column(name = "password", nullable = false, unique = true)
	private String senha;
	
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="lastAcess", nullable = false, unique=false)
	@Temporal(TemporalType.DATE)
	private Date ultimoAcesso;
	
	public int getId() {
		return id;
	}
	
	
}
	
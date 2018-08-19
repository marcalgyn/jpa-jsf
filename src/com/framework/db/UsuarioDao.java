package com.framework.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.framework.model.Usuario;

public class UsuarioDao {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-jsf");
	private EntityManager em = factory.createEntityManager();

	public Usuario getUsuario(String nomeUsuario, String senha) {
		
		try {

			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.nomeUsuario = :name and u.senha = :senha")
					.setParameter("name", nomeUsuario).setParameter("senha", senha).getSingleResult();
			return usuario;

		} catch (NoResultException e) {
			e.printStackTrace();
			return null;

		}
	}

	public boolean inserirUsuario(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		
		try {
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}
	
	public List listarUsuario() {
		
		Query queryObj = em.createQuery("SELECT u from Usuario u");
		List usuarioList = queryObj.getResultList();
		if (usuarioList != null && usuarioList.size() >0) {
			return usuarioList;
		} else {
			return null;
		}
	}

}

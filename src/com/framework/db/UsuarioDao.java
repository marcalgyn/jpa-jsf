package com.framework.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.internal.EntityManagerFactoryRegistry;

import com.framework.model.Usuario;

public class UsuarioDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-jsf");
	private EntityManager em = factory.createEntityManager();

	private EntityTransaction transacao = em.getTransaction();

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

	public Usuario getUsuarioCard(String nomeUsuario, String matricula) {

		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.nomeUsuario = :name and u.matricula = :matricula")
					.setParameter("name", nomeUsuario).setParameter("matricula", matricula).getSingleResult();
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean inserirUsuario(Usuario usuario) {

		try {

			if (!transacao.isActive()) {
				transacao.begin();
			}
			em.persist(usuario);
			transacao.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
			return false;
		}
	}

	public boolean deletarUsuario(Usuario usuario) {

		try {
			if (!transacao.isActive()) {
				transacao.begin();
			}
			em.remove(usuario);
			transacao.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
			return false;
		}
	}

	public List listarUsuario() {

		Query queryObj = em.createQuery("SELECT u from Usuario u");
		List usuarioList = queryObj.getResultList();
		if (usuarioList != null && usuarioList.size() > 0) {
			return usuarioList;
		} else {
			return null;
		}
	}

}

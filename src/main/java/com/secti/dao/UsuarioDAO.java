package com.secti.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.secti.model.Usuario;

public class UsuarioDAO extends GenericoDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Usuario buscarPorNomeUsuario(String nomeUsuario) {
		
		try {
			return em.createNamedQuery("Usuario.pesquisarUsuarioPorNomeUsuario", Usuario.class)
					.setParameter("nomeusuario", nomeUsuario).getSingleResult();
		} catch (NoResultException e) {
			System.err.println("Usuário não encontrado!");
		}
		
		return null;
	}

}

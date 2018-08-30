package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.UsuarioDAO;
import com.secti.model.Usuario;
import com.secti.service.UsuarioService;

public class UsuarioImpl implements UsuarioService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private @Inject UsuarioDAO dao;

	public void salvar(Usuario usuario) throws Exception {
		dao.salvar(usuario);
	}

	public List<Usuario> listarTodos() {
		return dao.listarTodos(Usuario.class);
	}

	public void remover(Usuario usuario, Long id) throws Exception {
		dao.remover(Usuario.class, id);
	}

	public Usuario buscarPorId(Usuario usuario, Long id){
		return dao.buscarPorId(Usuario.class, id);
	}

	public void editar(Usuario usuario) throws Exception {
		dao.editar(usuario);
	}

}

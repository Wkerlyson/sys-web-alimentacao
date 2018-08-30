package com.secti.service;

import java.util.List;

import com.secti.model.Usuario;

public interface UsuarioService {
	
	public void salvar(Usuario usuario) throws Exception;
	
	public List<Usuario> listarTodos();
	
	public void remover(Usuario usuario, Long id) throws Exception;
	
	public Usuario buscarPorId(Usuario usuario, Long id);
	
	public void editar(Usuario usuario) throws Exception;
	
}

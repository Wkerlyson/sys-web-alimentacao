package com.secti.service;

import java.util.List;

import com.secti.model.Produto;

public interface ProdutoService {
	public void salvar(Produto produto) throws Exception;
	
	public List<Produto> listarTodos();
	
	public void remover(Produto produto, Long id) throws Exception;
	
	public Produto  buscarPorId(Produto produto, Long id);
	
	public void editar(Produto produto) throws Exception;
}

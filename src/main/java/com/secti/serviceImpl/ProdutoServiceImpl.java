package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.ProdutoDAO;
import com.secti.model.Produto;
import com.secti.service.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO dao;

	public void salvar(Produto produto) throws Exception {
		dao.salvar(produto);
	}

	public List<Produto> listarTodos() {
		return dao.listarTodos(Produto.class);
	}

	public void remover(Produto produto, Long id) throws Exception {
		dao.remover(Produto.class, id);
	}

	public Produto buscarPorId(Produto produto, Long id) {
		return dao.buscarPorId(Produto.class, id);
	}

	public void editar(Produto produto) throws Exception {
		dao.editar(produto);
	}

}

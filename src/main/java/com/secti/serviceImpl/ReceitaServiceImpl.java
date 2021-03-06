package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.secti.dao.ReceitaDao;
import com.secti.model.Produto;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;
import com.secti.service.ReceitaService;

public class ReceitaServiceImpl implements ReceitaService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitaDao dao;

	public void salvar(Receita receita) throws Exception {
		dao.salvar(receita);
	}

	public List<Receita> listarTodos() {
		return dao.listarTodos(Receita.class);
	}

	public void remover(Receita receita, Long id) throws Exception {
		dao.remover(Receita.class, id);
	}

	public Receita buscarPorId(Receita receita, Long id) {
		return dao.buscarPorId(Receita.class, id);
	}

	public void editar(Receita receita) throws Exception {
		dao.editar(receita);
	}

	public void editarProdutoReceita(Receita receita, List<ProdutoReceita> produtos, List<Produto> produtosParaRemover)
			throws Exception {
		dao.editarProdutoReceita(receita, produtos, produtosParaRemover);
	}

	public void salvarProdutoReceita(Receita receita, List<ProdutoReceita> produtos) throws Exception {
		dao.salvarProdutoReceita(receita, produtos);
	}

	
	
}

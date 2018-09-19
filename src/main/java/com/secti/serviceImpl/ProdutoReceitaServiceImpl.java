package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.ProdutoReceitaDAO;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;
import com.secti.service.ProdutoReceitaService;

public class ProdutoReceitaServiceImpl implements ProdutoReceitaService, Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoReceitaDAO produtoReceitaDAO;
	
	public void inserirProdutos(ProdutoReceita produtoReceita) throws Exception {		
		produtoReceitaDAO.salvar(produtoReceita);
	}

	public void remover(ProdutoReceita produtoReceita, Long id) throws Exception {
		produtoReceitaDAO.remover(ProdutoReceita.class, id);
	}

	public List<ProdutoReceita> listarProdutos(Receita receita) {
		return produtoReceitaDAO.listarProdutos(receita);
	}
	
	

}

package com.secti.service;

import java.util.List;

import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;

public interface ProdutoReceitaService {
	
	public void inserirProdutos(ProdutoReceita produtoReceita) throws Exception;
	public void remover(ProdutoReceita produtoReceita, Long id) throws Exception;
	public List<ProdutoReceita> listarProdutos(Receita receita);
	
}

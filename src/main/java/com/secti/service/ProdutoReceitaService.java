package com.secti.service;

import com.secti.model.ProdutoReceita;

public interface ProdutoReceitaService {
	
	public void inserirProdutos(ProdutoReceita produtoReceita) throws Exception;
	public void remover(ProdutoReceita produtoReceita, Long id) throws Exception;
	
}

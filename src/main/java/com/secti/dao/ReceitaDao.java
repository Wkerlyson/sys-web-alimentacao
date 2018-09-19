package com.secti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.secti.model.Produto;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;
import com.secti.util.Transactional;

public class ReceitaDao extends GenericoDAO<Receita> {

	private static final long serialVersionUID = 1L;

	private @Inject EntityManager manager;

	@Transactional
	public void salvarProdutoReceita(Receita receita, List<ProdutoReceita> produtos) {

		manager.persist(receita);

		for (int i = 0; i < produtos.size(); i++) {
			ProdutoReceita produtoSelecionado = produtos.get(i);
			produtoSelecionado.setReceita(receita);
			manager.persist(produtoSelecionado);
		}
	}

	@Transactional
	public void editarProdutoReceita(Receita receita, List<ProdutoReceita> produtos, List<Produto> produtosParaRemover) {

		manager.merge(receita);

		for (int i = 0; i < produtos.size(); i++) {
			ProdutoReceita produtoSelecionado = produtos.get(i);
			produtoSelecionado.setReceita(receita);
			manager.merge(produtoSelecionado);
		}
		
		for (Produto prodParaRemover : produtosParaRemover) {
			Query q = manager.createQuery("delete from ProdutoReceita where produto =:produto AND receita =:receita")
					.setParameter("produto", prodParaRemover).setParameter("receita", receita);
			q.executeUpdate();			
		}


	}

}

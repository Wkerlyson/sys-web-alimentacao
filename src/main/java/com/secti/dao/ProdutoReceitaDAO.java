package com.secti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.secti.model.Produto;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;
import com.secti.util.Transactional;

public class ProdutoReceitaDAO extends GenericoDAO<ProdutoReceita>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public List<ProdutoReceita> listarProdutos(Receita receita){
		return em.createNamedQuery("ProdutoReceita.listarProdutos", ProdutoReceita.class).setParameter("receita", receita).getResultList();
	}
	
	@Transactional
	public void removerProduto(Produto produto, Receita receita) {
		Query q = em.createQuery("delete from ProdutoReceita where produto =:produto AND receita =:receita").setParameter("produto", produto).setParameter("receita", receita);
		q.executeUpdate();
	}
	
}

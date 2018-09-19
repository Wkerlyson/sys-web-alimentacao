package com.secti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;

public class ProdutoReceitaDAO extends GenericoDAO<ProdutoReceita>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public List<ProdutoReceita> listarProdutos(Receita receita){
		return em.createNamedQuery("ProdutoReceita.listarProdutos", ProdutoReceita.class).setParameter("receita", receita).getResultList();
	}
	
}

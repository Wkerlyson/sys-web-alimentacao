package com.secti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.secti.model.Cardapio;
import com.secti.model.CardapioReceita;

public class CardapioReceitaDAO extends GenericoDAO<CardapioReceita>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public List<CardapioReceita> listarReceitasPorCardapio(Cardapio cardapio){
		return em.createNamedQuery("CardapioReceita.listarReceitas", CardapioReceita.class).setParameter("cardapio", cardapio).getResultList();
	}
}

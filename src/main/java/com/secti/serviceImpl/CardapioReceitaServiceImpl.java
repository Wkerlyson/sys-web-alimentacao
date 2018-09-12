package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.CardapioReceitaDAO;
import com.secti.model.Cardapio;
import com.secti.model.CardapioReceita;
import com.secti.service.CardapioReceitaService;

public class CardapioReceitaServiceImpl implements CardapioReceitaService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CardapioReceitaDAO dao;
	
	public void salvar(CardapioReceita cardapioReceita) throws Exception {
		dao.salvar(cardapioReceita);
	}

	public List<CardapioReceita> listarReceitasPorCardapio(Cardapio cardapio) {
		return dao.listarReceitasPorCardapio(cardapio);
	}

	public void remover(CardapioReceita cardapioReceita, Long id) throws Exception {
		dao.remover(CardapioReceita.class, id);
	}

}

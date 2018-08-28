package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.CardapioDAO;
import com.secti.model.Cardapio;
import com.secti.service.CardapioService;

public class CardapioServiceImpl implements CardapioService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CardapioDAO dao;
	
	public void salvar(Cardapio cardapio) throws Exception {
		dao.salvar(cardapio);
	}

	public List<Cardapio> listarTodos() {
		return dao.listarTodos(Cardapio.class);
	}

	public void remover(Cardapio cardapio, Long id) throws Exception {
		dao.remover(Cardapio.class, id);
	}

	public Cardapio buscarPorId(Cardapio cardapio, Long id) {
		return dao.buscarPorId(Cardapio.class, id);
	}

	public void editar(Cardapio cardapio) throws Exception {
		dao.editar(cardapio);
	}

}

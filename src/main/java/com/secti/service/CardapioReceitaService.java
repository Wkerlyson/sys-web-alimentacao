package com.secti.service;

import java.util.List;

import com.secti.model.Cardapio;
import com.secti.model.CardapioReceita;

public interface CardapioReceitaService {
	public void salvar(CardapioReceita cardapioReceita) throws Exception;
	public List<CardapioReceita> listarReceitasPorCardapio(Cardapio cardapio);
	public void remover(CardapioReceita cardapioReceita, Long id) throws Exception;
}

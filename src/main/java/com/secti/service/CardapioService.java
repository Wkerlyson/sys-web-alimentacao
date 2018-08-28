package com.secti.service;

import java.util.List;

import com.secti.model.Cardapio;

public interface CardapioService {
	
	public void salvar(Cardapio cardapio) throws Exception;

	public List<Cardapio> listarTodos();

	public void remover(Cardapio cardapio, Long id) throws Exception;

	public Cardapio buscarPorId(Cardapio cardapio, Long id);

	public void editar(Cardapio cardapio) throws Exception;
}

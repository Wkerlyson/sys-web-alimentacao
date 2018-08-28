package com.secti.service;

import java.util.List;

import com.secti.model.Escola;

public interface EscolaService {
	
	public void salvar(Escola escola) throws Exception;

	public List<Escola> listarTodos();

	public void remover(Escola escola, Long id) throws Exception;

	public Escola buscarPorId(Escola escola, Long id);

	public void editar(Escola escola) throws Exception;
}

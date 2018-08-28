package com.secti.service;

import java.util.List;

import com.secti.model.Receita;

public interface ReceitaService {
	
	public void salvar(Receita receita) throws Exception;

	public List<Receita> listarTodos();

	public void remover(Receita receita, Long id) throws Exception;

	public Receita buscarPorId(Receita receita, Long id);

	public void editar(Receita receita) throws Exception;
}

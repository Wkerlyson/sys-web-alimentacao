package com.secti.service;

import java.util.List;

import com.secti.model.Programa;

public interface ProgramaService {
	
	public void salvar(Programa programa) throws Exception;
	
	public List<Programa> listarTodos();
	
	public void remover(Programa programa, Long id) throws Exception;
	
	public Programa buscarPorId(Programa programa, Long id);
	
	public void editar(Programa programa) throws Exception;
	
}

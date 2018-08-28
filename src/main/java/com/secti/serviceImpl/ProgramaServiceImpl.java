package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.ProgramaDAO;
import com.secti.model.Programa;
import com.secti.service.ProgramaService;

public class ProgramaServiceImpl implements ProgramaService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProgramaDAO dao;


	public void salvar(Programa programa) throws Exception {
		dao.salvar(programa);
	}

	public List<Programa> listarTodos() {
		return dao.listarTodos(Programa.class);
	}

	public void remover(Programa programa, Long id) throws Exception {
		dao.remover(Programa.class, id);
	}

	public Programa buscarPorId(Programa programa, Long id){
		programa = dao.buscarPorId(Programa.class, id);
		return programa;
	}

	public void editar(Programa programa) throws Exception {
		dao.editar(programa);
	}
	
}

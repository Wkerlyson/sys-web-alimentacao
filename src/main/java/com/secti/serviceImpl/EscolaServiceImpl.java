package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.EscolaDAO;
import com.secti.model.Escola;
import com.secti.service.EscolaService;

public class EscolaServiceImpl implements EscolaService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EscolaDAO dao;
	
	public void salvar(Escola escola) throws Exception {
		dao.salvar(escola);
	}

	public List<Escola> listarTodos() {
		return dao.listarTodos(Escola.class);
	}

	public void remover(Escola escola, Long id) throws Exception {
		dao.remover(Escola.class, id);
	}

	public Escola buscarPorId(Escola escola, Long id) {
		return dao.buscarPorId(Escola.class, id);
	}

	public void editar(Escola escola) throws Exception {
		dao.editar(escola);
	}

}

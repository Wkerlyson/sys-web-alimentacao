package com.secti.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.secti.dao.EscolaProgramaDAO;
import com.secti.model.Escola;
import com.secti.model.EscolaPrograma;
import com.secti.service.EscolaProgramaService;

public class EscolaProgramaImpl implements EscolaProgramaService, Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private @Inject EscolaProgramaDAO dao;

	public List<EscolaPrograma> listarProgramas(Escola escola) {
		return dao.listarProgramas(escola);
	}

	public void remover(EscolaPrograma escolaPrograma, Long id) throws Exception {
		dao.remover(EscolaPrograma.class, id);
	}

}

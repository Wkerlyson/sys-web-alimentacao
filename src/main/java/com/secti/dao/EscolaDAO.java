package com.secti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.secti.model.Escola;
import com.secti.model.EscolaPrograma;
import com.secti.util.Transactional;

public class EscolaDAO extends GenericoDAO<Escola>{

	private static final long serialVersionUID = 1L;
	
	private @Inject EntityManager manager;
	
	@Transactional
	public void salvarEscolaPrograma(Escola escola, List<EscolaPrograma> programas) {
		
		manager.persist(escola);
				
		for (int i = 0; i < programas.size(); i++) {
			EscolaPrograma programaSelecionado = programas.get(i);
			programaSelecionado.setEscola(escola);
			manager.persist(programaSelecionado);
		}
	}
	
	@Transactional
	public void editarEscolaPrograma(Escola escola, List<EscolaPrograma> programas) {
		
		manager.merge(escola);
				
		for (int i = 0; i < programas.size(); i++) {
			EscolaPrograma programaSelecionado = programas.get(i);
			programaSelecionado.setEscola(escola);
			manager.merge(programaSelecionado);
		}
	}

}

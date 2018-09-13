package com.secti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.secti.model.Escola;
import com.secti.model.EscolaPrograma;

public class EscolaProgramaDAO extends GenericoDAO<EscolaPrograma>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public List<EscolaPrograma> listarProgramas(Escola escola){
		return em.createNamedQuery("EscolaPrograma.listarProgramas", EscolaPrograma.class).setParameter("escola", escola).getResultList();
	}
	
}

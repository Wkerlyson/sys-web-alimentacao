package com.secti.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.secti.util.Transactional;

public abstract class GenericoDAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	

	public Class<T> tipo;

	public GenericoDAO() {
	}

	public GenericoDAO(Class<T> tipo) {
		this.tipo = tipo;
	}
	
	@Transactional
	public void salvar(T t) throws Exception {
		em.persist(t);
	}
	
	@Transactional
	public void editar(T t) throws Exception {
		em.merge(t);
	}
	
	@Transactional
	public void remover(Class<T> c, Long id) throws Exception {
		em.remove(em.find(c, id));
	}

	public T buscarPorId(Class<T> c, Long id){
		return em.find(c, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos(Class<T> c) {
		
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(c));
        
        Query q = em.createQuery(cq);
        return q.getResultList();

	}

}

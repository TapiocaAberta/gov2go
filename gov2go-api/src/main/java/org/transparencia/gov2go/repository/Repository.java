package org.transparencia.gov2go.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/*
 * 		Autor: William Siqueira
 * 		github: https://github.com/jesuino/jug-call4papers/blob/master/src/main/java/org/jugvale/call4papers/service/ServiceAbstrato.java
 * 
 */

@Stateless
@LocalBean
public abstract class Repository<T> {
	protected Class<T> tipo = retornaTipo();

	@PersistenceContext(unitName = "gov2go-unit")
	protected static EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(T entidade) throws Exception {
		em.persist(entidade);
	}

	public void remover(T entidade) throws Exception{
		em.remove(entidade);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscaTodos() throws Exception {
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(tipo));
		return (List<T>) em.createQuery(cq).getResultList();
	}

	public T buscarPorId(long id) throws Exception {
		return em.find(tipo, id);
	}
	
	public T atualizar(T entidade) throws Exception {
		return em.merge(entidade);
	}
	
	protected abstract Class<T> retornaTipo();
}

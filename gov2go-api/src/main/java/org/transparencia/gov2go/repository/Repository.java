package org.transparencia.gov2go.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
@SuppressWarnings("unchecked")
public abstract class Repository<T> {
	protected Class<T> tipo = retornaTipo();

	@PersistenceContext(unitName = "gov2go-unit")
	protected EntityManager em;

	public void novo(T entidade) {
		em.persist(entidade);
	}

	public void remover(T entidade) {
		em.remove(entidade);
	}

	public List<T> todos() {
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(tipo));
		return (List<T>) em.createQuery(cq).getResultList();
	}

	public T comID(long id) {
		return em.find(tipo, id);
	}

	public T atualizar(T entidade) {
		return em.merge(entidade);
	}

	/**
	 * 	@author Pedro Hos<br>
	 *
	 *  Utilizando Exemplo de Eduardo Guerra!
	 *  
	 *  https://groups.google.com/forum/#!topic/projeto-oo-guiado-por-padroes/pOIiOD9cifs
	 *
	 * Este método retorna o tipo da Classe, dessa maneira não é necessário cada Classe expor seu tipo!!!!
	 *
	 * @return Class<T>
	 */
	private Class<T> retornaTipo() {
		Class<?> clazz = this.getClass();
		
		while ( !clazz.getSuperclass().equals(Repository.class) ) {
			clazz = clazz.getSuperclass();
		}
		
		ParameterizedType tipoGenerico = (ParameterizedType) clazz.getGenericSuperclass();
		return (Class<T>) tipoGenerico.getActualTypeArguments()[0];
	}
}

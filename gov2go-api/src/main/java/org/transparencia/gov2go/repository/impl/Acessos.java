package org.transparencia.gov2go.repository.impl;

import javax.persistence.TypedQuery;

import org.transparencia.gov2go.model.impl.Acesso;
import org.transparencia.gov2go.repository.Repository;

public class Acessos extends Repository<Acesso> {

	public Acesso comToken(String token) {
		String jpql = "select a from Acesso a where a.token = :parametro";

		TypedQuery<Acesso> query = em.createQuery(jpql, Acesso.class);
		query.setParameter("parametro", token);

		Acesso acesso = query.getSingleResult();

		return acesso;
	}

}

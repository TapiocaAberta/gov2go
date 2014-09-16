package org.transparencia.gov2go.dao.impl;

import javax.persistence.Query;

import org.transparencia.gov2go.dao.Dao;
import org.transparencia.gov2go.model.impl.Acesso;

public class AcessoDao extends Dao<Acesso> {

	@Override
	protected Class<Acesso> retornaTipo() {
		return Acesso.class;
	}

	public Acesso buscarPorToken(String token) {
		String jpql = "select a from Acesso a where a.token = :parametro";

		Query query = em.createQuery(jpql, Acesso.class);
		query.setParameter("parametro", token);

		Acesso acesso = new Acesso();
		acesso = (Acesso) query.getSingleResult();

		return acesso;
	}

	public boolean acessoPermitido(String token) {
		boolean result = false;

		Acesso acesso = buscarPorToken(token);

		if (acesso != null)
			result = true;

		return result;
	}

}

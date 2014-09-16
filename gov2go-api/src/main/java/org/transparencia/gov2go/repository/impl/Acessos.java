package org.transparencia.gov2go.repository.impl;

import javax.persistence.Query;

import org.transparencia.gov2go.model.impl.Acesso;
import org.transparencia.gov2go.repository.Repository;

public class Acessos extends Repository<Acesso> {

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

package org.transparencia.gov2go.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.Repository;

@Stateless
public class Usuarios extends Repository<Usuario> {

	public Usuario comEmail(String email) {
		String jpql = "select a from Usuario a where a.email = :email";

		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("email", email);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	
	}
}

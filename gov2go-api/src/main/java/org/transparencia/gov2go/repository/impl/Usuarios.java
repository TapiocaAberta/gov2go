package org.transparencia.gov2go.repository.impl;

import javax.persistence.TypedQuery;

import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.Repository;

public class Usuarios extends Repository<Usuario> {

	public Usuario comEmail(String email) {
		String jpql = "select a from Usuario a where a.email = :parametro";

		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("parametro", email);

		Usuario usuario = query.getSingleResult();
	
		return usuario;
	}
}

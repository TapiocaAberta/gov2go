package org.transparencia.gov2go.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.Repository;

public class Usuarios extends Repository<Usuario> {

	public Usuario buscaPorEmail(String email) {
		String jpql = "select a from Usuario a where a.email = :parametro";

		Query query = em.createQuery(jpql, Usuario.class);
		query.setParameter("parametro", email);

		List<?> result = query.getResultList();
		
		Usuario usuario = null;
		
		if (!result.isEmpty())
			usuario = (Usuario) result.get(0);
		
		return usuario;
	}
}

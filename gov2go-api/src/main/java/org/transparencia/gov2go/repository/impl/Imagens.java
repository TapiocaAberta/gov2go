package org.transparencia.gov2go.repository.impl;

import javax.persistence.TypedQuery;

import org.transparencia.gov2go.model.impl.Imagem;
import org.transparencia.gov2go.repository.Repository;

public class Imagens extends Repository<Imagem> {

	public Imagem paraOcorrenciaComID(Long id) {
		
		String jpql = "select i from Imagem i where i.ocorrencia.id =:id";
		
		TypedQuery<Imagem> query = em.createQuery(jpql, Imagem.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}
}

package org.transparencia.gov2go.repository.impl;

import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.repository.Repository;

public class Ocorrencias extends Repository<Ocorrencia> {

	@Override
	protected Class<Ocorrencia> retornaTipo() {
		return Ocorrencia.class;
	}

}

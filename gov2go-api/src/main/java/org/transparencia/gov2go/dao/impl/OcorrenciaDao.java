package org.transparencia.gov2go.dao.impl;

import org.transparencia.gov2go.dao.Dao;
import org.transparencia.gov2go.model.impl.Ocorrencia;

public class OcorrenciaDao extends Dao<Ocorrencia> {

	@Override
	protected Class<Ocorrencia> retornaTipo() {
		return Ocorrencia.class;
	}

}

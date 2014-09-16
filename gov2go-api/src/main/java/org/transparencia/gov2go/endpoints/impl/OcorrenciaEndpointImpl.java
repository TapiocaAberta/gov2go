package org.transparencia.gov2go.endpoints.impl;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.endpoints.OcorrenciaEndpoint;
import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.services.impl.OcorrenciaService;

public class OcorrenciaEndpointImpl implements OcorrenciaEndpoint {

	@Inject
	private OcorrenciaService service;

	@Override
	public Response criar(Ocorrencia entidade) {
		return service.criar(entidade);
	}

	@Override
	public Response buscaPorId(Long id) {
		return service.buscarPorId(id);
	}

	@Override
	public Response listarTodos() {
		return service.listarTodos();
	}

	@Override
	public void atualizar(Long id, Ocorrencia entidade) {
		service.atualizar(id, entidade);
	}

}

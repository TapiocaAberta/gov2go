package org.transparencia.gov2go.endpoints.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.endpoints.OcorrenciaEndpoint;
import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.services.impl.OcorrenciaService;

@Stateless
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
	public Response atualizar(Long id, Ocorrencia entidade) {
		return service.atualizar(id, entidade);
	}

	@Override
	public Response buscaImagemParaOcorrencia(Long id) {
		return service.imagemParaOcorrenciaComID(id);
	}

	@Override
	public Response addImagemParaOcorrencia(Long id, HttpServletRequest request, byte[] imagem) {
		return service.novaImagemParaOcorrenciaComID(id, imagem, request.getContentType());
	}

}

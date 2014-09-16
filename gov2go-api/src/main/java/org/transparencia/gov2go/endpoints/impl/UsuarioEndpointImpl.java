package org.transparencia.gov2go.endpoints.impl;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.endpoints.UsuarioEndpoint;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.services.impl.UsuarioService;

public class UsuarioEndpointImpl implements UsuarioEndpoint {

	@Inject
	private UsuarioService service;
	
	@Override
	public Response criar(Usuario entidade) {
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
	public void atualizar(@PathParam("id") Long id, Usuario entidade) {
		service.atualizar(id, entidade);
	}

}

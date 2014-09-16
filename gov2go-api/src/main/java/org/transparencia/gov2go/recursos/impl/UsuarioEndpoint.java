package org.transparencia.gov2go.recursos.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.recursos.Endpoint;
import org.transparencia.gov2go.services.impl.UsuarioService;

@Stateless
@Path("/usuario")
public class UsuarioEndpoint implements Endpoint<Usuario> {

	@Inject
	private UsuarioService service;
	
	@Override
	@POST
	@Consumes("application/json")
	public Response criar(Usuario entidade) {
		return service.criar(entidade);
	}

	@Override
	public Response buscaPorId(@PathParam("id") Long id) {
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

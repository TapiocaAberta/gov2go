package org.transparencia.gov2go.services.impl;

import static javax.ws.rs.core.Response.Status.CONFLICT;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.transparencia.gov2go.endpoints.UsuarioEndpoint;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Usuarios;
import org.transparencia.gov2go.services.Service;

public class UsuarioService implements Service <Usuario>{

	@Inject
	private Usuarios usuarios;
	
	@Inject
	Logger log;
	
	@Override
	public Response criar(Usuario entidade) {
		
		if (usuarioExiste(entidade.getEmail())) {
			log.info( "Usuario: " + entidade.getEmail() + " existe!" );
			return Response.status(CONFLICT).build();
		}

		usuarios.novo(entidade);
		return recursoCriado(UsuarioEndpoint.class, entidade.getId(), entidade);
	}

	@Override
	public Response buscarPorId(Long id) {
		Usuario usuario = lanca404SeNulo( usuarios.comID(id) );
		return Response.ok(usuario).build();
	}

	@Override
	public Response listarTodos() {
		return Response.ok( usuarios.todos() ).build();
	}

	@Override
	public Response atualizar(Long id, Usuario entidade) {
		lanca404SeNulo( usuarios.comID(id) );
		entidade.setId(id);
		return Response.ok(entidade).build();
	}
	
	public Response buscaUsuarioComEmail(String email) {
		Usuario usuario = lanca404SeNulo( usuarios.comEmail(email) );
		return Response.ok(usuario).build();
	}

	protected boolean usuarioExiste( String email ) {
		Usuario usuario = usuarios.comEmail(email);
		return usuario != null;
	}
	

}

package org.transparencia.gov2go.services;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public interface Service <T> {

	Response criar(T entidade);

	Response buscarPorId(Long id);

	Response listarTodos();

	Response atualizar(Long id, T entidade);

	@SuppressWarnings("hiding")
	default <T> T lanca404SeNulo(T object, String message) {
		if (object == null) {
			throw new WebApplicationException(Response.status(NOT_FOUND).entity(message).build());
		}
		return object;
	}
	
	@SuppressWarnings("hiding")
	default <T> T lanca404SeNulo(T object) {
		if (object == null) {
			throw new WebApplicationException(Response.status(NOT_FOUND).build());
		}
		return object;
	}

}

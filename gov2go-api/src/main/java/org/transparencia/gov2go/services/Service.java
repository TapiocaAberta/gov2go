package org.transparencia.gov2go.services;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.transparencia.gov2go.model.ModelDefault;

public interface Service <T extends ModelDefault> {

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
	
	
	default Response recursoCriado(Class<?> resource, Long id, String mimeType, T entidade) {
		return Response.created( UriBuilder.fromResource(resource)
								.path(String.valueOf(id)).build() )
								.entity(entidade)
								.type(mimeType)
								.build();
	}
	
	default Response recursoCriado(Class<?> resource, Long id, T entidade) {
		return recursoCriado(resource, id, "application/json", entidade);
	}

}

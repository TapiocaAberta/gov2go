package org.transparencia.gov2go.recursos;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface Endpoint<T> {

	@POST
	@Consumes("application/json")
	public abstract Response criar(T entidade);

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public abstract Response buscaPorId(@PathParam("id") Long id);
	
	@GET
	@Produces("application/json")
	public abstract Response listarTodos();

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public abstract void atualizar(@PathParam("id") Long id, T entidade);
}

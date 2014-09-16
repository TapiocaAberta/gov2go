package org.transparencia.gov2go.endpoints;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.model.impl.Ocorrencia;

@Stateless
@Path("ocorrencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OcorrenciaEndpoint {

	@POST
	public Response criar(Ocorrencia entidade);

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response buscaPorId(@PathParam("id") Long id);

	@GET
	public Response listarTodos();

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public void atualizar(@PathParam("id") Long id, Ocorrencia entidade);

}

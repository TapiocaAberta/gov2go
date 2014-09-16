package org.transparencia.gov2go.recursos.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.services.impl.AcessoService;

@Stateless
@Path("/acesso")
public class AcessoEndpoint {
	
	@Inject
	private AcessoService service;
	
	@GET
	@Produces("application/json")
	public Response criar() {
		return service.criar();
	}

}

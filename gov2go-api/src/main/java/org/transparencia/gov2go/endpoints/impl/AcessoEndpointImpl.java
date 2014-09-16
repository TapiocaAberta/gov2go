package org.transparencia.gov2go.endpoints.impl;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.transparencia.gov2go.endpoints.AcessoEndpoint;
import org.transparencia.gov2go.services.impl.AcessoService;

public class AcessoEndpointImpl implements AcessoEndpoint {
	
	@Inject
	private AcessoService service;
	
	@Override
	public Response criar() {
		return service.criar();
	}

}

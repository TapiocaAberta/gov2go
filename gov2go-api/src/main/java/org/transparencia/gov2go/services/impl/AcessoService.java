package org.transparencia.gov2go.services.impl;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
import org.transparencia.gov2go.dao.impl.AcessoDao;
import org.transparencia.gov2go.model.impl.Acesso;

public class AcessoService {

	@Inject
	AcessoDao dao;
	
	public Response criar() {
		
		String token = RandomStringUtils.random(30, true, true);
		
		Acesso acesso  = new Acesso();
		acesso.setToken(token);
		
		try {
			dao.salvar(acesso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Status.OK).entity(acesso).build();
	}
	
}

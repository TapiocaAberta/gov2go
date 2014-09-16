package org.transparencia.gov2go.services.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ServiceUtil {

	public static Response retorna404SeEhNulo(Object object) {
		if (object == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.status(Status.OK).entity(object).build();
	}
}

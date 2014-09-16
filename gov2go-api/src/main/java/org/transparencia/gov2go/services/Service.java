package org.transparencia.gov2go.services;

import javax.ws.rs.core.Response;

public interface Service <T> {
	
	Response criar(T entidade);
	Response buscarPorId (Long id);
	Response listarTodos();
	Response atualizar(Long id, T entidade);
	
}

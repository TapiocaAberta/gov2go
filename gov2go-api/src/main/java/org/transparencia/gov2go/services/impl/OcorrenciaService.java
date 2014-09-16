package org.transparencia.gov2go.services.impl;

import static org.transparencia.gov2go.services.util.ServiceUtil.retorna404SeEhNulo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Ocorrencias;
import org.transparencia.gov2go.repository.impl.Usuarios;
import org.transparencia.gov2go.services.Service;

public class OcorrenciaService implements Service <Ocorrencia>{

	@Inject
	Ocorrencias ocorrenciaDao;
	
	@Inject
	Usuarios usuarioDao;
	
	@Inject
	Logger log;
	
	@Override
	public Response criar(Ocorrencia entidade) {
		
		try {
			Usuario usuario = usuarioDao.buscaPorEmail(entidade.getUsuario().getEmail());
			entidade.setUsuario(usuario);
			ocorrenciaDao.salvar(entidade);
			
		} catch (Exception e) {
			log.error("Erro ao criar nova ocorrencia: " + e.getMessage());
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		return Response.ok().build();
		
	}

	@Override
	public Response buscarPorId(Long id) {
		
		Ocorrencia ocorrencia = null;
		try {
			ocorrencia = ocorrenciaDao.buscarPorId(id);
		} catch (Exception e) {
			log.error("Erro ao Buscar ocorrencia por ID: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(ocorrencia);
	}

	@Override
	public Response listarTodos() {
		
		List<Ocorrencia> todos = null;
		try {
			todos = ocorrenciaDao.buscaTodos();
		} catch (Exception e) {
			log.error("Erro ao listar usuarios: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(todos);
	}

	@Override
	public Response atualizar(Long id, Ocorrencia entidade) {
		
		Ocorrencia ocorrencia = null;
		try {
			ocorrencia = ocorrenciaDao.atualizar(entidade);
		} catch (Exception e) {
			log.error("Erro ao atualizar usuario: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(ocorrencia);
	}


}

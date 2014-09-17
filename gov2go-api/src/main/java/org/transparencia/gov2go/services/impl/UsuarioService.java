package org.transparencia.gov2go.services.impl;

import static org.transparencia.gov2go.services.util.ServiceUtil.retorna404SeEhNulo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Usuarios;
import org.transparencia.gov2go.services.Service;

public class UsuarioService implements Service <Usuario>{

	@Inject
	private Usuarios usuarioDao;
	
	@Inject
	Logger log;
	
	@Override
	public Response criar(Usuario entidade) {
		
		try {
			
			if(usuarioExiste(entidade.getEmail())) {
				log.info("Usuario: " + entidade.getEmail() + " existe!");
				return Response.ok().build();
			}
			
			usuarioDao.novo(entidade);
		} catch (Exception e) {
			log.error("Erro ao criar novo usuario: " + e.getMessage());
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}

	@Override
	public Response buscarPorId(Long id) {
		
		Usuario usuario = null;
		try {
			usuario = usuarioDao.comID(id);
		} catch (Exception e) {
			log.error("Erro ao Buscar usuario por ID: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(usuario);
	}

	@Override
	public Response listarTodos() {
		
		List<Usuario> todos = null;
		try {
			todos = usuarioDao.todos();
		} catch (Exception e) {
			log.error("Erro ao listar usuarios: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(todos);
	}

	@Override
	public Response atualizar(Long id, Usuario entidade) {
		
		Usuario usuario = null;
		try {
			usuario = usuarioDao.atualizar(entidade);
		} catch (Exception e) {
			log.error("Erro ao atualizar usuario: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(usuario);
	}

	protected boolean usuarioExiste( String email ) {
		Usuario usuario = usuarioDao.comEmail(email);
		return usuario != null;
	}
	

}

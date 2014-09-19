package org.transparencia.gov2go.services.impl;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static org.transparencia.gov2go.model.constantes.ExtensoesArquivo.EXTENSOES;
import static org.transparencia.gov2go.services.util.ServiceUtil.retorna404SeEhNulo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.transparencia.gov2go.endpoints.OcorrenciaEndpoint;
import org.transparencia.gov2go.model.impl.Imagem;
import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Imagens;
import org.transparencia.gov2go.repository.impl.Ocorrencias;
import org.transparencia.gov2go.repository.impl.Usuarios;
import org.transparencia.gov2go.services.Service;

public class OcorrenciaService implements Service <Ocorrencia>{

	@Inject
	Ocorrencias ocorrencias;
	
	@Inject
	Usuarios usuarios;
	
	@Inject
	Imagens imagens;
	
	@Inject
	Logger log;
	
	@Override
	public Response criar(Ocorrencia entidade) {
		
		try {
			Usuario usuario = usuarios.comEmail(entidade.getUsuario().getEmail());
			entidade.setUsuario(usuario);
			ocorrencias.novo(entidade);
			
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
			ocorrencia = ocorrencias.comID(id);
		} catch (Exception e) {
			log.error("Erro ao Buscar ocorrencia por ID: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(ocorrencia);
	}

	@Override
	public Response listarTodos() {
		
		List<Ocorrencia> todos = null;
		try {
			todos = ocorrencias.todos();
		} catch (Exception e) {
			log.error("Erro ao listar usuarios: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(todos);
	}

	@Override
	public Response atualizar(Long id, Ocorrencia entidade) {
		
		Ocorrencia ocorrencia = null;
		try {
			ocorrencia = ocorrencias.atualizar(entidade);
		} catch (Exception e) {
			log.error("Erro ao atualizar usuario: " + e.getMessage());
		}
		
		return retorna404SeEhNulo(ocorrencia);
	}
	
	public Response imagemParaOcorrenciaComID(Long id) {
		
		Ocorrencia ocorrencia = ocorrencias.comID(id);
		
		if ( ocorrencia == null )
			throw new WebApplicationException(Response.status(NOT_FOUND).build());
		
		Imagem imagem = imagens.paraOcorrenciaComID(id);
		
		if(imagem == null)
			throw new WebApplicationException(Response.status(NOT_FOUND).build());
		
		return Response.ok(imagem.getImagem()).type(imagem.getMimeType()).build();
	}
	
	public Response novaImagemParaOcorrenciaComID(Long id, byte[] dados, String mimeType) {
		
		Ocorrencia ocorrencia = ocorrencias.comID(id);
		
		if ( ocorrencia == null )
			throw new WebApplicationException(Response.status(NOT_FOUND).build());
		
		Imagem imagem = new Imagem();
		
		imagem.setImagem(dados);
		imagem.setNome("ocorrencia_" + id + "_" + ocorrencia.getTipoOcorrencia().toString());
		imagem.setMimeType(mimeType);
		imagem.setExtensao(EXTENSOES.get(mimeType));
		imagem.setOcorrencia(ocorrencia);
		
		ocorrencia.setImagem(imagem);
		ocorrencias.atualizar(ocorrencia);
		
		return Response.created( UriBuilder.fromResource(OcorrenciaEndpoint.class)
							  		.path(String.valueOf(id) + "/imagem")
							  		.build() ).build();
	}

}

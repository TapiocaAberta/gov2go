package org.transparencia.gov2go.services.impl;

import static org.transparencia.gov2go.model.constantes.ExtensoesArquivo.EXTENSOES;

import java.util.List;

import javax.inject.Inject;
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
	public Response criar(Ocorrencia ocorrencia) {
		Usuario usuario = usuarios.comEmail(ocorrencia.getUsuario().getEmail()); //TODO: Precisa Disso????? Verificar isto Oo Ta Zuado :(
		lanca404SeNulo(usuario);
		ocorrencia.setUsuario(usuario);
		ocorrencias.novo(ocorrencia);
		return Response.ok().build();
		
	}

	@Override
	public Response buscarPorId(Long id) {
		Ocorrencia ocorrencia = ocorrencias.comID(id);
		return Response.ok( lanca404SeNulo(ocorrencia) ).build();
	}

	@Override
	public Response listarTodos() {
		List<Ocorrencia> todos = ocorrencias.todos();
		return Response.ok(todos).build();
	}

	@Override
	public Response atualizar(Long id, Ocorrencia entidade) {
		lanca404SeNulo(ocorrencias.comID(id));
		entidade.setId(id);
		return Response.ok(ocorrencias.atualizar(entidade)).build();
	}
	
	public Response imagemParaOcorrenciaComID(Long id) {
		lanca404SeNulo( ocorrencias.comID(id) );
		Imagem imagem = lanca404SeNulo(imagens.paraOcorrenciaComID(id));
		return Response.ok(imagem.getImagem()).type(imagem.getMimeType()).build();
	}
	
	public Response novaImagemParaOcorrenciaComID(Long id, byte[] dados, String mimeType) {
		
		Ocorrencia ocorrencia = lanca404SeNulo( ocorrencias.comID(id) );
		
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

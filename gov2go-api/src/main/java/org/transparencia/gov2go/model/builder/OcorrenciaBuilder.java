package org.transparencia.gov2go.model.builder;

import java.time.LocalDate;

import org.transparencia.gov2go.model.constantes.Status;
import org.transparencia.gov2go.model.constantes.Tipo;
import org.transparencia.gov2go.model.impl.Imagem;
import org.transparencia.gov2go.model.impl.Localizacao;
import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.model.impl.Usuario;

public class OcorrenciaBuilder {

	private Ocorrencia ocorrencia;

	public OcorrenciaBuilder() {
		novaOcorrencia();
	}
	
	public OcorrenciaBuilder(Status status) {
		novaOcorrencia();
		ocorrencia.setStatus(status);
	}

	private void novaOcorrencia() {
		ocorrencia = new Ocorrencia();
	}
	
	public Ocorrencia build() {
		return ocorrencia;
	}
	
	public OcorrenciaBuilder status( Status status ) {
		ocorrencia.setStatus(status);
		return this;
	}
	
	public OcorrenciaBuilder hoje() {
		ocorrencia.setDataOcorrencia( LocalDate.now() );
		return this;
	}
	
	public OcorrenciaBuilder dataOcorrencia(LocalDate data) {
		ocorrencia.setDataOcorrencia( data );
		return this;
	}
	
	public OcorrenciaBuilder titulo(String titulo) {
		ocorrencia.setTitulo(titulo);
		return this;
	}
	
	public OcorrenciaBuilder descricao(String descricao) {
		ocorrencia.setDescricao(descricao);
		return this;
	}
	
	public OcorrenciaBuilder doTipo(Tipo tipo) {
		ocorrencia.setTipo(tipo);
		return this;
	}
	
	public OcorrenciaBuilder localizadoEm (Localizacao localizacao) {
		ocorrencia.setLocalizacao(localizacao);
		return this;
	}
	
	public OcorrenciaBuilder doUsuario (Usuario usuario) {
		ocorrencia.setUsuario(usuario);
		return this;
	}
	
	public OcorrenciaBuilder comFoto (Imagem imagem) {
		ocorrencia.setImagem(imagem);
		return this;
	}
	
}

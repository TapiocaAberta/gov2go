package org.transparencia.gov2go.model.impl;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.transparencia.gov2go.model.ModelDefault;

@Entity
@Table(name = "imagem")
public class Imagem extends ModelDefault {

	private static final long serialVersionUID = 1L;
	
	public Imagem () {}
	
	public Imagem (byte[] imagem) {
		this.imagem = imagem;
	}
	
	@Column(nullable = false)
	private String mimeType;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String extensao;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] imagem;
	
	@JsonIgnore
	@OneToOne
	private Ocorrencia ocorrencia;

	
	public byte[] getImagem() {
		return imagem;
	}
	
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrenia) {
		this.ocorrencia = ocorrenia;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}

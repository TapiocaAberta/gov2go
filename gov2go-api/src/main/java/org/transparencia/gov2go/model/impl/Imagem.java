package org.transparencia.gov2go.model.impl;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] imagem;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
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

}

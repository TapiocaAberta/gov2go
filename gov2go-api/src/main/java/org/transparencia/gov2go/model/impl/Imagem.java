package org.transparencia.gov2go.model.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.transparencia.gov2go.model.ModelDefault;

@Entity
@Table(name = "imagem")
@XmlRootElement
public class Imagem extends ModelDefault {

	private static final long serialVersionUID = 1L;
	
	public Imagem () {}
	
	public Imagem (String imagem) {
		this.imagem = imagem;
	}
	
	
	@Column
	private String imagem;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Ocorrencia ocorrencia;

	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrenia) {
		this.ocorrencia = ocorrenia;
	}

}

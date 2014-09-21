package org.transparencia.gov2go.model.impl;

import static org.transparencia.gov2go.model.constantes.Status.ABERTA;
import static org.transparencia.gov2go.model.constantes.Status.ANDAMENTO;
import static org.transparencia.gov2go.model.constantes.Status.FINALIZADA;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.transparencia.gov2go.model.ModelDefault;
import org.transparencia.gov2go.model.builder.OcorrenciaBuilder;
import org.transparencia.gov2go.model.constantes.Status;
import org.transparencia.gov2go.model.constantes.Tipo;
import org.transparencia.gov2go.util.rest.JsonLocalDateSerializer;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia extends ModelDefault {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "data_criacao_ocorrencia", nullable = false)
	@JsonProperty("data") 
	@JsonSerialize(using = JsonLocalDateSerializer.class)
	private LocalDate dataCriacaoOcorrencia;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status = ABERTA;

	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToOne(mappedBy = "ocorrencia", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(nullable = false)
	private Localizacao localizacao;
	
	@OneToOne(mappedBy = "ocorrencia", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Imagem imagem;
	
	@Enumerated (EnumType.STRING)
	@Column(name = "tipo_ocorrencia", nullable = false)
	private Tipo tipo;
	
	public static OcorrenciaBuilder nova () {
		return new OcorrenciaBuilder();
	}
	
	public static OcorrenciaBuilder aberta () {
		return new OcorrenciaBuilder(ABERTA);
	}
	public static OcorrenciaBuilder finalizada () {
		return new OcorrenciaBuilder(FINALIZADA);
	}
	public static OcorrenciaBuilder andamento () {
		return new OcorrenciaBuilder(ANDAMENTO);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
		localizacao.setOcorrencia(this);
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
		imagem.setOcorrencia(this);
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipoOcorrencia) {
		this.tipo = tipoOcorrencia;
	}

	public LocalDate getDataCriacaoOcorrencia() {
		return dataCriacaoOcorrencia;
	}

	public void setDataOcorrencia(LocalDate dataCriacaoOcorrencia) {
		this.dataCriacaoOcorrencia = dataCriacaoOcorrencia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}

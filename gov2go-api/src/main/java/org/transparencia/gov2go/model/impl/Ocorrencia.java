package org.transparencia.gov2go.model.impl;

import static org.transparencia.gov2go.constantes.StatusOcorrencia.ABERTA;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.transparencia.gov2go.constantes.StatusOcorrencia;
import org.transparencia.gov2go.constantes.TipoOcorrencia;
import org.transparencia.gov2go.model.ModelDefault;

@Entity
@Table(name = "ocorrencia")
@XmlRootElement
public class Ocorrencia extends ModelDefault {

	private static final long serialVersionUID = 1L;
	
	public Ocorrencia() {}
	
	public Ocorrencia(String titulo, String descricao, Usuario usuario, Localizacao localizacao, Imagem imagem, TipoOcorrencia tipoOcorrencia) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.localizacao = localizacao;
		this.imagem = imagem;
		this.tipoOcorrencia = tipoOcorrencia;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao_ocorrencia", nullable = false)
	@JsonProperty("dataOcorrencia")
	private Date dataCriacaoOcorrencia;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusOcorrencia status = ABERTA;

	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToOne(mappedBy = "ocorrencia", cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Localizacao localizacao;
	
	@OneToOne(mappedBy = "ocorrencia", cascade = CascadeType.ALL)
	private Imagem imagem;
	
	@Enumerated (EnumType.STRING)
	@Column(name = "tipo_ocorrencia", nullable = false)
	private TipoOcorrencia tipoOcorrencia;

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

	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public Date getDataCriacaoOcorrencia() {
		return dataCriacaoOcorrencia;
	}

	public void setDataCriacaoOcorrencia(Date dataCriacaoOcorrencia) {
		this.dataCriacaoOcorrencia = dataCriacaoOcorrencia;
	}

	public StatusOcorrencia getStatus() {
		return status;
	}

	public void setStatus(StatusOcorrencia status) {
		this.status = status;
	}

}

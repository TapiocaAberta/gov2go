package org.transparencia.gov2go.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.transparencia.gov2go.model.ModelDefault;
import org.transparencia.gov2go.model.builder.LocalizacaoBuilder;
import org.transparencia.gov2go.model.constantes.Provedor;

@Entity
@Table(name = "localizacao")
public class Localizacao extends ModelDefault {

	private static final long serialVersionUID = 1L;

	@Column(updatable = false, nullable = false)
	private String latitude;

	@Column(updatable = false, nullable = false)
	private String longitude;

	@Enumerated(EnumType.STRING)
	@Column(length = 7, nullable = false)
	private Provedor provedor;

	@JsonIgnore
	@OneToOne
	private Ocorrencia ocorrencia;
	
	public static LocalizacaoBuilder gps () {
		return new LocalizacaoBuilder(Provedor.GPS);
	}
	
	public static LocalizacaoBuilder nerwork () {
		return new LocalizacaoBuilder(Provedor.NETWORK);
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Provedor getProvedor() {
		return provedor;
	}

	public void setProvedor(Provedor provedor) {
		this.provedor = provedor;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

}

package org.transparencia.gov2go.model.builder;

import org.transparencia.gov2go.model.constantes.Provedor;
import org.transparencia.gov2go.model.impl.Localizacao;
import org.transparencia.gov2go.model.impl.Ocorrencia;

public class LocalizacaoBuilder {
	
	private Localizacao localizacao;
	
	public LocalizacaoBuilder( ) {
		novaLocalizacao();
	}
	
	public LocalizacaoBuilder(Provedor provedor) {
		novaLocalizacao();
		localizacao.setProvedor(provedor);
	}
	
	private void novaLocalizacao () {
		localizacao = new Localizacao();
	}
	
	public Localizacao build () {
		return localizacao;
	}
	
	public LocalizacaoBuilder comProvedor ( Provedor provedor ) {
		localizacao.setProvedor(provedor);
		return this;
	}
	
	public LocalizacaoBuilder paraOcorrencia ( Ocorrencia ocorrencia ) {
		localizacao.setOcorrencia(ocorrencia);
		return this;
	}
	
	public LocalizacaoBuilder comLatitude ( String latitude) {
		localizacao.setLatitude(latitude);
		return this;
	}
	
	public LocalizacaoBuilder comLongitude ( String longitude ) {
		localizacao.setLongitude(longitude);
		return this;
	}

}

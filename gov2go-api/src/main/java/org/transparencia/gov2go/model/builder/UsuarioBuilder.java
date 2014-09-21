package org.transparencia.gov2go.model.builder;

import java.util.ArrayList;
import java.util.List;

import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.model.impl.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	private List<Ocorrencia> ocorrencias;
	
	public UsuarioBuilder() {
		usuario = new Usuario();
		ocorrencias = new ArrayList<Ocorrencia>();
	}
	
	public Usuario build () {
		return usuario;
	}
	
	public UsuarioBuilder comNome( String nome ) {
		usuario.setNome(nome);
		return this;
	}
	
	public UsuarioBuilder comEmail ( String email ) {
		usuario.setEmail(email);
		return this;
	}
	
	public UsuarioBuilder paraOcorrencia ( Ocorrencia ocorrencia ) {
		ocorrencias.add(ocorrencia);
		return this;
	}
	
	
}

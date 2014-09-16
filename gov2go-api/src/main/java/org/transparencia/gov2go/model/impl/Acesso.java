package org.transparencia.gov2go.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.transparencia.gov2go.model.ModelDefault;

@Entity
@Table(name = "acessos")
public class Acesso extends ModelDefault {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

package org.transparencia.gov2go.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.transparencia.gov2go.model.ModelDefault;

@Entity
@Table(name = "usuario")
@XmlRootElement
public class Usuario extends ModelDefault {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario() {}
	
	public Usuario(String nome, String email, List<Ocorrencia> ocorrencias) {
		this.nome = nome;
		this.email = email;
		this.ocorrencias = ocorrencias;
	}

	@Column
	private String nome;
	
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Ocorrencia> ocorrencias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

}

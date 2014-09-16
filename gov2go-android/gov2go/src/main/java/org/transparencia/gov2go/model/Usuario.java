package org.transparencia.gov2go.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * Created by pedrosjc on 14/04/14.
 */
public class Usuario {
    private static final long serialVersionUID = 1L;

    public Usuario() {
    }

    public Usuario(String nome, String email, List<Ocorrencia> ocorrencias) {
        this.nome = nome;
        this.email = email;
        this.ocorrencias = ocorrencias;
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    private String nome;

    private String email;

    @JsonIgnore
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

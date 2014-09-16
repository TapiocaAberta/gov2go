package org.transparencia.gov2go.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * Created by pedrosjc on 14/04/14.
 */
public class Imagem implements Serializable {

    private static final long serialVersionUID = 1L;

    public Imagem() {}


    public Imagem(byte[] imagem) {
        this.imagem = imagem;
    }

    private byte[] imagem;

    @JsonIgnore
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

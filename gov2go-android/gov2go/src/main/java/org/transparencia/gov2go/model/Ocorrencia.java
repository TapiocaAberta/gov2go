package org.transparencia.gov2go.model;

import static org.transparencia.gov2go.util.constantes.StatusOcorrencia.ABERTA;
import org.codehaus.jackson.annotate.JsonProperty;
import org.transparencia.gov2go.util.constantes.StatusOcorrencia;
import org.transparencia.gov2go.util.constantes.TipoOcorrencia;

import java.util.Date;

/**
 * Created by pedrosjc on 14/04/14.
 */
public class Ocorrencia {

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

    @JsonProperty("dataOcorrencia")
    private Date dataCriacaoOcorrencia;

    private StatusOcorrencia status = ABERTA;

    private String titulo;

    private String descricao;

    private Usuario usuario;

    private Localizacao localizacao;

    private Imagem imagem;

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

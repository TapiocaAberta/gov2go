package org.transparencia.gov2go.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.transparencia.gov2go.util.constantes.Provedor;

/**
 * Created by pedrosjc on 14/04/14.
 */
public class Localizacao {

    private static final long serialVersionUID = 1L;

    public Localizacao () {}

    public Localizacao(String latitude, String longitude, Provedor provedor) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.provedor = provedor;
    }

    private String latitude;

    private String longitude;

    private Provedor provedor;

    @JsonIgnore
    private Ocorrencia ocorrencia;

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

package org.transparencia.gov2go.recursos.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.transparencia.gov2go.model.Ocorrencia;
import org.transparencia.gov2go.recursos.WebService;
import org.transparencia.gov2go.recursos.WebServiceAbstract;

import static org.transparencia.gov2go.util.AndroidUtils.logI;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.URL_NOVA_OCORRENCIA;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.URL_NOVO_USUARIO;

/**
 * Created by pedrosjc on 11/04/14.
 */
public class OcorrenciaWebService extends WebServiceAbstract implements WebService<Ocorrencia> {

    @Override
    public Ocorrencia get() throws Exception {return null;}

    @Override
    public boolean post(Ocorrencia objeto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(objeto);

        logI(json);

        String[] result = postRESTFileContent(URL_NOVA_OCORRENCIA, json);

        if(result != null) {
            String status = result[0];

            logI("Status: " + status);

            if (status.equals("200")) {
                return true;
            }
        }

        return false;
    }
}

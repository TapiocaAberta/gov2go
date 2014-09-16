package org.transparencia.gov2go.recursos.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.transparencia.gov2go.model.Usuario;
import org.transparencia.gov2go.recursos.WebService;
import org.transparencia.gov2go.recursos.WebServiceAbstract;

import static org.transparencia.gov2go.util.AndroidUtils.logI;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.URL_NOVO_USUARIO;

/**
 * Created by pedrosjc on 14/04/14.
 */
public class UsuarioWebService extends WebServiceAbstract implements WebService<Usuario> {

    @Override
    public Usuario get() throws Exception {
        return null;
    }

    @Override
    public boolean post(Usuario objeto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(objeto);

        logI(json);

        String[] result = postRESTFileContent(URL_NOVO_USUARIO, json);

        if(result != null) {
            String status = result[0];

            logI("Status: " + status);

            if (status.equals("200")) {
                logI("Mensagem: Sucesso ao gravar novo ususario");
                return true;
            }
        }

        return false;
    }
}

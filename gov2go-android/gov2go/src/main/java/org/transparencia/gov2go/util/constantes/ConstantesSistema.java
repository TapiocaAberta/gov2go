package org.transparencia.gov2go.util.constantes;

import java.util.HashMap;
import org.transparencia.gov2go.R;

/**
 * Created by pedrosjc on 08/04/14.
 */
public class ConstantesSistema {

    /*
     *  URLs
     */

    protected static final String URL_PRINCIPAL = "http://gov2go.trysoft.com.br/api/";
    public static final String URL_NOVA_OCORRENCIA = URL_PRINCIPAL + "ocorrencia/";
    public static final String URL_NOVO_USUARIO = URL_PRINCIPAL + "usuario/";

    public static final int CAMERA = 0;
    public static final int GALERIA = 1;

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;

    public static final String LOG = "gov2go";
    public static final String IMAGEM = "imagem";
    public static final String TITULO = "titulo";
    public static final String TITULO_OCORRENCIA = "titulo ocorrencia";
    public static final String MENSAGEM = "mensagem";


    public static final HashMap<String, Integer> imagensLista() {
        HashMap<String, Integer> mapImagens = new HashMap<String, Integer>();

        mapImagens.put("Iluminação publica", R.drawable.ic_lampada);
        mapImagens.put("Sinal de Trânsito", R.drawable.ic_semaforo);
        mapImagens.put("Buraco em Vias Públicas", R.drawable.ic_cone);
        mapImagens.put("Dengue", R.drawable.ic_mosquito);
        mapImagens.put("Poda e Retirada de Árvores", R.drawable.ic_tree);
        mapImagens.put("Bueiros", R.drawable.ic_bueiro);
        mapImagens.put("Estacionamento Irregular", R.drawable.ic_no_parking);
        mapImagens.put("Ocupação Irregular", R.drawable.ic_house);
        mapImagens.put("Remoção de Entulhos", R.drawable.ic_trash_recycle_bin_garbage);
        mapImagens.put("Controle de Roedores", R.drawable.ic_rato);
        mapImagens.put("Limpeza Urbana", R.drawable.ic_broom);
        mapImagens.put("Esgoto", R.drawable.ic_esgoto);
        mapImagens.put("Outros", R.drawable.ic_arrow_points_arrow);

        return mapImagens;

    }

    public static final HashMap<String, TipoOcorrencia> tiposOcorrenciaMap() {
        HashMap<String, TipoOcorrencia> mapTipo = new HashMap<String, TipoOcorrencia>();

        mapTipo.put("Iluminação publica", TipoOcorrencia.ILUMINACAO_PUBLICA);
        mapTipo.put("Sinal de Trânsito", TipoOcorrencia.SINAL_TRANSITO);
        mapTipo.put("Buraco em Vias Públicas", TipoOcorrencia.BURACO_VIAS);
        mapTipo.put("Dengue", TipoOcorrencia.DENGUE);
        mapTipo.put("Poda e Retirada de Árvores", TipoOcorrencia.PODA_RETIRADA_ARVORES);
        mapTipo.put("Bueiros", TipoOcorrencia.BUEIROS);
        mapTipo.put("Estacionamento Irregular", TipoOcorrencia.ESTACIONAMENTO_IRREGULAR);
        mapTipo.put("Ocupação Irregular", TipoOcorrencia.OCUPACAO_IRREGULAR);
        mapTipo.put("Remoção de Entulhos", TipoOcorrencia.REMOCAO_ENTULHOS);
        mapTipo.put("Controle de Roedores", TipoOcorrencia.CONTROLE_ROEDORES);
        mapTipo.put("Limpeza Urbana", TipoOcorrencia.LIMPEZA_URBANA);
        mapTipo.put("Esgoto", TipoOcorrencia.ESGOTO);
        mapTipo.put("Outros", TipoOcorrencia.OUTROS);

        return mapTipo;

    }

    public static class HashBD {
        public static final String TABELA_HASH = "hash";
        public static final String ID_HASH = "_id";
        public static final String CHAVE = "chave";

        public static final String[] COLUNAS_HASH = new String[] {ID_HASH, CHAVE};

    }

    public static class UsuarioBD {
        public static final String TABELA_USUARIO = "usuario";
        public static final String ID_USUARIO = "_id";
        public static final String NOME = "nome";
        public static final String EMAIL = "email";

        public static final String[] COLUNAS_USUARIO = new String[] {ID_USUARIO, NOME, EMAIL};
    }
}

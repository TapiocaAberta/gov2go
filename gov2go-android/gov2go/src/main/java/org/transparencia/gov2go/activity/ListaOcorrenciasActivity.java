package org.transparencia.gov2go.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import org.transparencia.gov2go.R;
import org.transparencia.gov2go.util.constantes.TipoOcorrencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.CHAVE;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.IMAGEM;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.TITULO;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.tiposOcorrenciaMap;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.imagensLista;

/**
 * Created by pedrosjc on 09/04/14.
 */
public class ListaOcorrenciasActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private HashMap<String, TipoOcorrencia> mapTipo;
    private List<Map<String, Object>> ocorrencias;
    private String chave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mapTipo = tiposOcorrenciaMap();
        this.chave = getIntent().getStringExtra(CHAVE);

        String[] de = {IMAGEM, TITULO};
        int[] para = {R.id.ic_tipoOcorrencia, R.id.tipoOcorrencia};

        SimpleAdapter adapter = new SimpleAdapter(this, montaListaTipoOcorrencia(), R.layout.lista_ocorrencias, de, para);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Map<String, Object> map = ocorrencias.get(i);
        String tipoSelecionado = (String) map.get(TITULO);

        Intent intent = new Intent(this, NovaOcorrenciaActivity.class);
        intent.putExtra(TITULO, tipoSelecionado);
        intent.putExtra(CHAVE, chave);

        startActivity(intent);
    }

    public List<Map<String, Object>> montaListaTipoOcorrencia() {

        ocorrencias = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        String chave = "";

        for (Object key : mapTipo.keySet()) {

            chave = key.toString();
            item = new HashMap<String, Object>();

            item.put(IMAGEM, imagensLista().get(chave));
            item.put(TITULO, chave);

            ocorrencias.add(item);
        }

        return ocorrencias;

    }
}

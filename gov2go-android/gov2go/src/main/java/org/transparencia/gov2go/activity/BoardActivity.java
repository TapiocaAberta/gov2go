package org.transparencia.gov2go.activity;

import static org.transparencia.gov2go.util.AndroidUtils.*;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.CHAVE;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.transparencia.gov2go.R;



public class BoardActivity extends ActionBarActivity {

    private String chave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        this.chave = getIntent().getStringExtra(CHAVE);
    }

    public void novaOcorrencia( View view ) {
        Intent intent = new Intent(this, ListaOcorrenciasActivity.class);
        intent.putExtra(CHAVE, chave);
        startActivity(intent);
    }

    public void informacoes ( View view ) {
        gerarToast("Em Contrução", this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}

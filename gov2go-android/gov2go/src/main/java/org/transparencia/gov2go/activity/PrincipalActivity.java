package org.transparencia.gov2go.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.transparencia.gov2go.R;
import org.transparencia.gov2go.dao.Dao;

import static org.transparencia.gov2go.util.AndroidUtils.gerarLogDeAcordoComResultado;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.CHAVE;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.ID_HASH;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.TABELA_HASH;


public class PrincipalActivity extends ActionBarActivity {

    private SQLiteDatabase db;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        this.dao = new Dao(this);
        this.db = dao.getDb();
    }

    public void entrarOnClick(View view) {
        irParaDashboard();
    }

    protected void irParaDashboard() {

        String chave = "";

        String sql = "SELECT " + CHAVE + " FROM " + TABELA_HASH;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            chave = cursor.getString(0);
            cursor.close();

        } else {
            ContentValues values = new ContentValues();

            chave = "Ads123$54@22312312323123"; //TODO: Pegar do Serviço depois que for implementado

            values.put(ID_HASH, 1L);
            values.put(CHAVE, chave);

            long resultado = db.insert(TABELA_HASH, null, values);
            gerarLogDeAcordoComResultado(resultado);
        }

        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra(CHAVE, chave);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

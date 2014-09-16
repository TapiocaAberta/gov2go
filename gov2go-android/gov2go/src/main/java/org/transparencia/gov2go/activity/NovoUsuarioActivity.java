package org.transparencia.gov2go.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.transparencia.gov2go.R;
import org.transparencia.gov2go.dao.Dao;
import org.transparencia.gov2go.model.Usuario;
import org.transparencia.gov2go.recursos.impl.UsuarioWebService;

import static org.transparencia.gov2go.util.AndroidUtils.gerarLogDeAcordoComResultado;
import static org.transparencia.gov2go.util.AndroidUtils.gerarToast;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.EMAIL;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.ID_USUARIO;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.NOME;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.TABELA_USUARIO;

public class NovoUsuarioActivity extends ActionBarActivity {

    private Dao dao;
    private SQLiteDatabase db;

    private EditText nome, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        dao = new Dao(this);
        db = dao.getDb();

        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email_user);
    }

    public void salvarUsuario(View view) {

        String nome = this.nome.getText().toString();
        String email = this.email.getText().toString();

        if (nome.equals("") || nome.equals(null)) {
            gerarToast("É Necessário preencher um nome", this);
            return;
        }

        if (email.equals(null) || email.equals("")) {
            gerarToast("É Necessário preencher um email válido", this);
            return;
        }

        Usuario user = new Usuario(nome, email);
        UsuarioWebService ws = new UsuarioWebService();

        boolean resultWS = false;

        try {
            resultWS = ws.post(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resultWS) {
            ContentValues values = new ContentValues();

            values.put(ID_USUARIO, 1L);
            values.put(NOME, nome);
            values.put(EMAIL, email);

            long resultBD = db.insert(TABELA_USUARIO, null, values);
            gerarLogDeAcordoComResultado(resultBD, this);

        } else {
            gerarToast("Aconteceu um erro ao criar usuario", this);
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.novo_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

}

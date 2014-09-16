package org.transparencia.gov2go.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.CHAVE;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.ID_HASH;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.TABELA_HASH;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.EMAIL;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.ID_USUARIO;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.NOME;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.TABELA_USUARIO;
import static org.transparencia.gov2go.util.AndroidUtils.*;

/**
 * Created by pedrosjc on 20/04/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BDGov2Go";
    private static final int VERSAO_BD = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABELA_USUARIO + " (" +
                ID_USUARIO + " INTEGER PRIMARY KEY," +
                NOME + " VARCHAR(50)," +
                EMAIL + " VARCHAR(50));";

        sqLiteDatabase.execSQL(sql);

        logI("Tabela USUARIO Criada");


        sql = "CREATE TABLE " + TABELA_HASH + " (" +
                ID_HASH + " INTEGER PRIMARY KEY," +
                CHAVE + " TEXT);";

        sqLiteDatabase.execSQL(sql);

        logI("Tabela HASH Criada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

package org.transparencia.gov2go.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.transparencia.gov2go.R;
import org.transparencia.gov2go.dao.Dao;
import org.transparencia.gov2go.model.Imagem;
import org.transparencia.gov2go.model.Localizacao;
import org.transparencia.gov2go.model.Ocorrencia;
import org.transparencia.gov2go.model.Usuario;
import org.transparencia.gov2go.recursos.impl.OcorrenciaWebService;
import org.transparencia.gov2go.util.constantes.Provedor;
import org.transparencia.gov2go.util.constantes.TipoOcorrencia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

import static org.transparencia.gov2go.util.AndroidUtils.gerarToast;
import static org.transparencia.gov2go.util.AndroidUtils.isNetworkAvailable;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.CAMERA;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.GALERIA;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HEIGHT;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.HashBD.CHAVE;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.TITULO;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.COLUNAS_USUARIO;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.UsuarioBD.TABELA_USUARIO;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.WIDTH;
import static org.transparencia.gov2go.util.constantes.ConstantesSistema.tiposOcorrenciaMap;

public class NovaOcorrenciaActivity extends ActionBarActivity implements DialogInterface.OnClickListener {

    private byte[] arrayImage;
    private AlertDialog dialog;
    private ImageView foto;
    private TextView tituloTipoOcorrencia;
    private EditText titulo, mensagem;
    private Uri uri;
    private File caminhoImagem;
    private TipoOcorrencia tipoOcorrencia;
    private String latitude, longitude, provedor;
    private LocationManager locationManager;
    private String chave;
    private SQLiteDatabase db;
    private Dao dao;
    private Bitmap imagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_ocorrencia);

        this.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Listener listener = new Listener();

        long tempoAtualizacao = 0;
        long distancia = 0;

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tempoAtualizacao, distancia, listener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempoAtualizacao, distancia, listener);

        String chaveTipoSelecionado = getIntent().getStringExtra(TITULO);

        this.tipoOcorrencia = tiposOcorrenciaMap().get(chaveTipoSelecionado);
        this.titulo = (EditText) findViewById(R.id.titulo);
        this.mensagem = (EditText) findViewById(R.id.textoMensagem);
        this.foto = (ImageView) findViewById(R.id.nova_foto);


        this.dialog = criarAlertDialog();
        this.chave = getIntent().getStringExtra(CHAVE);
        this.tituloTipoOcorrencia = (TextView) findViewById(R.id.id_tipoOcorrencia);


        tituloTipoOcorrencia.setText(chaveTipoSelecionado);

        this.dao = new Dao(this);
        this.db = dao.getDb();

    }

    public void pegarFoto(View view) {
        criarAlertDialog().show();
    }

    public void enviarOcorrencia(View view) {

        if (arrayImage == null) {
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            String key = preferences.getString(CHAVE, "");

            gerarToast("Selecione uma imagem da galeria ou tire uma nova Foto. " + key, this);
            return;
        }

        if (isNetworkAvailable(this)) {

            Ocorrencia ocorrencia = getOcorrencia();

            if (ocorrencia.getUsuario() == null) {
                gerarToast("É Necessário cadastrar um usuário", this);
                startActivity(new Intent(this, NovoUsuarioActivity.class));
                return;
            }

            OcorrenciaWebService ocorrenciaWS = new OcorrenciaWebService();

            Boolean resultado = false;
            try {
                resultado = ocorrenciaWS.post(ocorrencia);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (resultado) {
                gerarToast("Ocorrência Criada com Sucesso", this);

                Intent intent = new Intent(this, BoardActivity.class);
                startActivity(intent);

            } else {
                gerarToast("Ocorreu um erro, tente mais tarde!", this);
            }

        } else {
            gerarToast("Verifique se sua rede está conectada.", this);
        }


    }

    protected Ocorrencia getOcorrencia() {

        Usuario usuario = getUsuario();
        Localizacao localizacao = new Localizacao(latitude, longitude, Provedor.valueOf(provedor.toUpperCase()));
        Imagem imagem = getImagem();

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDataCriacaoOcorrencia(new Date());
        ocorrencia.setDescricao(mensagem.getText().toString());
        ocorrencia.setLocalizacao(localizacao);
        ocorrencia.setImagem(imagem);
        ocorrencia.setTitulo(titulo.getText().toString());
        ocorrencia.setUsuario(usuario);
        ocorrencia.setTipoOcorrencia(tipoOcorrencia);

        return ocorrencia;
    }

    //TODO: Passar para Base64
    protected Imagem getImagem() {
        return new Imagem("Porreta".getBytes());
    }

    protected Usuario getUsuario() {

        Cursor cursor = db.query(TABELA_USUARIO, COLUNAS_USUARIO, null, null, null, null, null);

        Usuario usuario = null;

        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuario();
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            cursor.close();

        }

        return usuario;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    private class Listener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            provedor = location.getProvider();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int item) {

        switch (item) {
            case CAMERA:
                enviarParaCamera();
                break;
            case GALERIA:
                enviarParaGaleria();
                break;
        }
    }

    protected void enviarParaCamera() {
        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String nomeImagem = diretorio.getPath() + "/" + System.currentTimeMillis() + ".jpg";

        caminhoImagem = new File(nomeImagem);

        uri = Uri.fromFile(caminhoImagem);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, CAMERA);
    }

    protected void enviarParaGaleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALERIA);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALERIA:
                    fotoGaleria(data);
                    break;
                case CAMERA:
                    fotoCamera(data);
            }
        }
    }

    protected void fotoCamera(Intent data) {

        int targetW = foto.getWidth();
        int targetH = foto.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(caminhoImagem.getAbsolutePath(), bmOptions);

        salvarNaGaleria();

        foto.setImageBitmap(bitmap);

        foto.getLayoutParams().height = HEIGHT;
        foto.getLayoutParams().width = WIDTH;

    }

    protected void salvarNaGaleria() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    protected void fotoGaleria(Intent data) {
        uri = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

        String filePath = cursor.getString(columnIndex);
        cursor.close();

        imagemSelecionada = BitmapFactory.decodeFile(filePath);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagemSelecionada.compress(Bitmap.CompressFormat.PNG, 100, stream);

        arrayImage = stream.toByteArray();

        foto.setImageBitmap(imagemSelecionada);

        foto.getLayoutParams().height = HEIGHT;
        foto.getLayoutParams().width = WIDTH;
    }

    protected AlertDialog criarAlertDialog() {
        final CharSequence[] itens = {getString(R.string.foto_camera), getString(R.string.foto_galeria)};
        AlertDialog.Builder alertBuilder;
        alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(R.string.titulo_fotos);
        alertBuilder.setItems(itens, (DialogInterface.OnClickListener) this);

        return alertBuilder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nova_ocorrencia, menu);
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
}

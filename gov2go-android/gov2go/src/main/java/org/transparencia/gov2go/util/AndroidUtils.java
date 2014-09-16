package org.transparencia.gov2go.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.transparencia.gov2go.util.constantes.ConstantesSistema;

/**
 * Created by pedrosjc on 08/04/14.
 */
public class AndroidUtils {

    public static void gerarLogDeAcordoComResultado(long resultado) {
        if(resultado != -1) {
            logI("Objeto Armazenado com Sucesso");
        } else {
            logE("Erro ao armazenar Objeto");
        }
    }

    public static void gerarLogDeAcordoComResultado(long resultado, Context context) {
        if(resultado != -1) {
            logI("Objeto Armazenado com Sucesso");
            gerarToast("Armazenado com Sucesso", context);
        } else {
            logE("Erro ao armazenar Objeto");
            gerarToast("Erro ao Armazenar", context);
        }
    }

    public static void sair(Intent intent, Context context) {
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void logI(Object o) {
        Log.i(ConstantesSistema.LOG, o.toString());
    }

    public static void logE(Object o) {
        Log.e(ConstantesSistema.LOG, o.toString());
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void gerarToast(CharSequence message, Context applicarionContext) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(applicarionContext, message, duration);
        toast.show();
    }
}

package org.transparencia.gov2go.recursos;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.transparencia.gov2go.util.AndroidUtils.logI;

/**
 * Created by pedrosjc on 11/04/14.
 */
public abstract class WebServiceAbstract {

    protected ObjectMapper objectMapper = null;
    protected JsonFactory jsonFactory = null;
    protected JsonParser jsonParser = null;

    public WebServiceAbstract() {
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }

    private String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    protected String[] getRESTFileContent(String url) throws Exception {
        logI("GET: " + url);

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        String[] result = new String[2];

        if (entity != null) {
            InputStream inputStream = entity.getContent();
            result[0] = String.valueOf(response.getStatusLine().getStatusCode());
            result[1] = toString(inputStream);

            inputStream.close();
            return result;
        }
        return null;
    }

    protected String[] postRESTFileContent(String url, String json) throws Exception {
        logI("POST: " + url + " JSON: " + json);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");
        httpPost.setEntity(input);

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String[] result = new String[2];

        if (entity != null) {
            InputStream inputStream = entity.getContent();
            result[0] = String.valueOf(response.getStatusLine().getStatusCode());
            result[1] = toString(inputStream);

            inputStream.close();
            return result;
        }

        return null;
    }
}

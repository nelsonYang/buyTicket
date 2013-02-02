package com.nelson.buyticket.httpclient;

import com.nelson.buyticket.json.JsonParser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Administrator
 */
public class HttpGetRequestor implements Requestor {

    private DefaultHttpClient httpClient;
    private String url;
    private String jsonHeaderName;
    private String jsonUrlName;
    private String imageUrl = "c:/test.jpg";

    public HttpGetRequestor(DefaultHttpClient httpClient, String url, String jsonHeaderName) {
        this.httpClient = httpClient;
        this.url = url;
        this.jsonHeaderName = jsonHeaderName;
    }

    public HttpGetRequestor(String url, String jsonHeaderName, DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
        this.url = url;
        this.jsonHeaderName = jsonHeaderName;
    }

    public HttpGetRequestor(String url, String jsonHeaderName, DefaultHttpClient httpClient, String imageUrl) {
        this.httpClient = httpClient;
        // this.jsonUrlName = jsonUrlName;
        this.url = url;
        this.jsonHeaderName = jsonHeaderName;
        this.imageUrl = imageUrl;
    }

    @Override
    public String request() {
        String result = "";
        HttpGet httpGet = new HttpGet(this.url);
        Map<String, String> headerMap = JsonParser.parseJson(this.jsonHeaderName, "header");
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            HttpResponse response = response = httpClient.execute(httpGet);
            //通过类型判断
            HttpEntity httpEntity = response.getEntity();
            String contentType = httpEntity.getContentType().getValue();
            if (contentType.contains("image")) {
                InputStream is = response.getEntity().getContent();
                byte[] images = new byte[(int) response.getEntity().getContentLength()];
                is.read(images);
                System.out.println(images);
                File file = new File(imageUrl);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(images);
                is.close();
                fos.close();
                result = imageUrl;
            } else {
                result = EntityUtils.toString(httpEntity);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpGetRequestor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpGet.abort();
        }
        return result;

    }
}

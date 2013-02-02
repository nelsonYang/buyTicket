package com.nelson.buyticket.httpclient;

import com.nelson.buyticket.json.JsonParser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Administrator
 */
public class HttpPostRequestor implements Requestor {

    private DefaultHttpClient httpClient;
    private String url;
    private String jsonHeaderName;
    private String jsonUrlName;
    private List<BasicNameValuePair> nameValuePairs;
    private String jsonParamName;

    public HttpPostRequestor(DefaultHttpClient httpClient, String url, String jsonHeaderName, String jsonParamName) {
        this.httpClient = httpClient;
        this.url = url;
        this.jsonHeaderName = jsonHeaderName;
        this.jsonParamName = jsonParamName;
        if (this.jsonParamName != null && !jsonParamName.isEmpty()) {
            if (nameValuePairs == null) {
                nameValuePairs = new ArrayList<BasicNameValuePair>(36);
            }
            Map<String, String> paraMap = JsonParser.parseJson(this.jsonParamName, "params");
            BasicNameValuePair paramParam;
            for (Map.Entry<String, String> entry : paraMap.entrySet()) {
                System.out.println("param:" + entry.getKey() + ":" + entry.getValue());
                paramParam = new BasicNameValuePair(entry.getKey(), entry.getValue());
                nameValuePairs.add(paramParam);
            }
        }
    }

    public HttpPostRequestor(DefaultHttpClient httpClient, String jsonUrlName, String jsonHeaderName, List<BasicNameValuePair> nameValuePairs) {
        this.httpClient = httpClient;
        this.url = jsonUrlName;
        this.jsonHeaderName = jsonHeaderName;
        this.nameValuePairs = nameValuePairs;
    }

    @Override
    public String request() {
        String result = "";
        HttpPost httpost = new HttpPost(this.url);
        Map<String, String> headerMap = JsonParser.parseJson(this.jsonHeaderName, "header");
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            httpost.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            if (nameValuePairs != null && !nameValuePairs.isEmpty()) {
                httpost.setEntity(new UrlEncodedFormEntity(this.nameValuePairs, "UTF-8"));
            }
            HttpResponse response = httpClient.execute(httpost);
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getEntity().getContentLength());
            String content = EntityUtils.toString(response.getEntity());
            result = content;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpPostRequestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpPostRequestor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpost.abort();
        }
        return result;
    }

    /**
     * @return the nameValuePairs
     */
    public List<BasicNameValuePair> getNameValuePairs() {
        return nameValuePairs;
    }
}

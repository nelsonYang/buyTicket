package com.nelson.buyticket.handler;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.httpclient.HttpPostRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
import com.nelson.buyticket.requestenum.RequestEnum;
import com.nelson.configuration.ConfigurationReader;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Administrator
 */
public class LoginSuggesionHandler implements Handler{
    private HttpPostRequestor httpPostRequestor;
    @Override
    public String handler() {
         RequestEntity requestEntity = ConfigurationReader.getRequestMap().get(RequestEnum.LoginSuggestNew);
         httpPostRequestor = new HttpPostRequestor(SSLHttpClient.getSSLHttpClient(),requestEntity);
         String content = httpPostRequestor.request();
         String loginRand = JSONObject.fromObject(content).getString("loginRand");
         return loginRand;
    }
    
}

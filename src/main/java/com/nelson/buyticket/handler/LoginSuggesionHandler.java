package com.nelson.buyticket.handler;

import com.nelson.buyticket.httpclient.HttpPostRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
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
         httpPostRequestor = new HttpPostRequestor(SSLHttpClient.getSSLHttpClient(), "https://dynamic.12306.cn/otsweb/loginAction.do?method=loginAysnSuggest", "/loginSuggest.json",new ArrayList<BasicNameValuePair>());
         String content = httpPostRequestor.request();
         String loginRand = JSONObject.fromObject(content).getString("loginRand");
         return loginRand;
    }
    
}

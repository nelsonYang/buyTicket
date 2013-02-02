package com.nelson.buyticket.handler;

import com.nelson.buyticket.httpclient.HttpPostRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Administrator
 */
public class LoginHandler implements Handler {
    private final ParameterThreadLocal threadLocal;
    private HttpPostRequestor httpPostRequestor;

    public LoginHandler(ParameterThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public String handler() {
        List<BasicNameValuePair> parameterList = new ArrayList<BasicNameValuePair>(9);
        String loginRand = threadLocal.get("loginRand");
        System.out.println("loginRand = " + loginRand);
        String randCode = threadLocal.get("randCode");
         System.out.println("randCode = " + randCode);
        BasicNameValuePair loginRandParam = new BasicNameValuePair("loginRand", loginRand);
        BasicNameValuePair refundLoginParam = new BasicNameValuePair("refundLogin", "N");
        BasicNameValuePair refundFlagParam = new BasicNameValuePair("refundFlag", "Y");
        BasicNameValuePair userNameParam = new BasicNameValuePair("loginUser.user_name", "nelsonyang_2012");
        BasicNameValuePair passwordParam = new BasicNameValuePair("user.password", "yjh123456789");
        BasicNameValuePair nameErrorFocusParam = new BasicNameValuePair("nameErrorFocus", "");
        BasicNameValuePair passwordErrorFocusParam = new BasicNameValuePair("passwordErrorFocus", "");
        BasicNameValuePair randCodeParam = new BasicNameValuePair("randCode", randCode);
        BasicNameValuePair randErrorFocusParam = new BasicNameValuePair("randErrorFocus", "");
        parameterList.add(loginRandParam);
        parameterList.add(refundLoginParam);
        parameterList.add(refundFlagParam);
        parameterList.add(userNameParam);
        parameterList.add(passwordParam);
        parameterList.add(nameErrorFocusParam);
        parameterList.add(passwordErrorFocusParam);
        parameterList.add(randCodeParam);
        parameterList.add(randErrorFocusParam);
        httpPostRequestor = new HttpPostRequestor(SSLHttpClient.getSSLHttpClient(), "https://dynamic.12306.cn/otsweb/loginAction.do?method=login", "/login.json", parameterList);
        String content = httpPostRequestor.request();
        return content;
    }
}

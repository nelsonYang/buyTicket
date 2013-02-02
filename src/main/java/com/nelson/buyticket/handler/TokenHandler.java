package com.nelson.buyticket.handler;

import com.nelson.buyticket.html.HtmlParser;
import com.nelson.buyticket.httpclient.HttpGetRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;

/**
 *
 * @author Administrator
 */
public class TokenHandler implements Handler {

    private HttpGetRequestor httpGetRequestor;

    @Override
    public String handler() {
        //获取token
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(), "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init", "/order.json");
        String content = httpGetRequestor.request();
        return HtmlParser.parseToken(content);
    }
}

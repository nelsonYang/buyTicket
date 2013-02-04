package com.nelson.buyticket.handler;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.html.HtmlParser;
import com.nelson.buyticket.httpclient.HttpGetRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
import com.nelson.buyticket.requestenum.RequestEnum;
import com.nelson.configuration.ConfigurationReader;

/**
 *
 * @author Administrator
 */
public class TokenAndLeftTicketNumHandler implements Handler {

    private HttpGetRequestor httpGetRequestor;

    @Override
    public String handler() {
        //获取token
        RequestEntity requestEntity = ConfigurationReader.getRequestMap().get(RequestEnum.ConfirmPassengerInitNew);
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(),requestEntity);
        String content = httpGetRequestor.request();
        return HtmlParser.parseTokenLeftTicketNumber(content).toJson();
    }
}

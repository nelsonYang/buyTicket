package com.nelson.buyticket.handler;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.httpclient.HttpGetRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
import com.nelson.buyticket.requestenum.RequestEnum;
import com.nelson.configuration.ConfigurationReader;

/**
 *
 * @author Administrator
 */
public class LeftTicketHandler implements Handler {

    private HttpGetRequestor httpGetRequestor;

    @Override
    public String handler() {
        RequestEntity requestEntity = ConfigurationReader.getRequestMap().get(RequestEnum.QueryLeftTicketNew);
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(), requestEntity);
        return httpGetRequestor.request();
    }
}

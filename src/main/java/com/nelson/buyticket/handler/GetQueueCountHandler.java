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
public class GetQueueCountHandler implements Handler {

 
    private HttpGetRequestor httpGetRequestor;

   

    @Override
    public String handler() {
        RequestEntity requestEntity = ConfigurationReader.getRequestMap().get(RequestEnum.GetQueueCountNew);
        //method=getQueueCount&train_date=2013-02-08&train_no=6i00000G7200&station=G72&seat=M&from=IOQ&to=BXP&ticket=O093650001M1479500039292350001",
        requestEntity.getUrl().replace(null, null);
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(), requestEntity);
        return httpGetRequestor.request();
    }
}

package com.nelson.buyticket.handler;

import com.nelson.buyticket.httpclient.HttpGetRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;

/**
 *
 * @author Administrator
 */
public class GetQueueCountHandler implements Handler {

 
    private HttpGetRequestor httpGetRequestor;

   

    @Override
    public String handler() {
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(), "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=getQueueCount&train_date=2013-02-01&train_no=65000D701204&station=D7012&seat=7&from=SZQ&to=GGQ&ticket=800795041170099500708007953000", "/getQueueCount.json");
        return httpGetRequestor.request();
    }
}

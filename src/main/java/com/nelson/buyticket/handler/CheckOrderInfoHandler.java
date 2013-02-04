package com.nelson.buyticket.handler;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.httpclient.HttpPostRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
import com.nelson.buyticket.requestenum.RequestEnum;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;
import com.nelson.configuration.ConfigurationReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Administrator
 */
public class CheckOrderInfoHandler implements Handler {

    private final ParameterThreadLocal threadLocal;
    private HttpPostRequestor httpPostRequestor;

    public CheckOrderInfoHandler(ParameterThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public String handler() {
        String secondRandCode = threadLocal.get("secondRandCode");
        System.out.println("secondRandCode = " + secondRandCode);
        List<BasicNameValuePair> parameterList = new ArrayList<BasicNameValuePair>(36);
        BasicNameValuePair paramParam = new BasicNameValuePair("randCode",secondRandCode);
        parameterList.add(paramParam);
        for (int i = 0; i < 3; i++) {
            paramParam = new BasicNameValuePair("checkbox9", "Y");
            parameterList.add(paramParam);
            paramParam = new BasicNameValuePair("oldPassengers", "");
            parameterList.add(paramParam);
        }
        paramParam = new BasicNameValuePair("orderRequest.to_station_name", "广州东");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_name", "杨金火");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("textfield", "中文或拼音首字母");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.from_station_name", "深圳");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passengerTickets", "7,0,1,杨金火,B,654321,13728656169,Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("oldPassengers", "杨金火,B,654321");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("org.apache.struts.taglib.html.TOKEN", threadLocal.get("token"));
        parameterList.add(paramParam);
        RequestEntity requestEntity = ConfigurationReader.getRequestMap().get(RequestEnum.CheckOrderInfoNew);
        httpPostRequestor = new HttpPostRequestor(SSLHttpClient.getSSLHttpClient(),requestEntity);
        httpPostRequestor.getNameValuePairs().addAll(parameterList);
        String content = httpPostRequestor.request();
        return content;
    }
}

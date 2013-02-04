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
public class BookTicketHanlder implements Handler {

    private final ParameterThreadLocal threadLocal;
    private HttpPostRequestor httpPostRequestor;

    public BookTicketHanlder(ParameterThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public String handler() {
        List<BasicNameValuePair> parameterList = new ArrayList<BasicNameValuePair>(36);
        BasicNameValuePair paramParam = new BasicNameValuePair("org.apache.struts.taglib.html.TOKEN", threadLocal.get("token"));
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("leftTicketStr", "800795041170099500708007953000");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("textfield", "中文或拼音首字母");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("checkbox0", "0");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.train_date", "2013-02-01");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.train_no", "65000D701204");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.station_train_code", "D7012");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.from_station_telecodee", "SZQ");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.to_station_telecode", "GGQ");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.seat_type_code", "");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.ticket_type_order_num", "");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.bed_level_order_num", "000000000000000000000000000000");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.start_time", "06:20");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.end_time", "07:39");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.from_station_name", "深圳");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.to_station_name", "广州东");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.cancel_flag", "1");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.id_mode", "Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passengerTickets", "7,0,1,杨金火,B,654321,13728656169,Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("oldPassengers", "杨金火,B,654321");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_seat", "7");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_ticket", "1");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_name", "杨金火");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_cardtype", "B");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_cardno", "654321");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("passenger_1_mobileno", "13728656169");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("checkbox9", "Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("oldPassengers", "");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("checkbox9", "Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("oldPassengers", "");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("checkbox9", "Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("oldPassengers", "");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("checkbox9", "Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("oldPassengers", "");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("checkbox9", "Y");
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("randCode", threadLocal.get("secondRandCode"));
        parameterList.add(paramParam);
        paramParam = new BasicNameValuePair("orderRequest.reserve_flag", "A");
        parameterList.add(paramParam);
        RequestEntity requestEntity = ConfigurationReader.getRequestMap().get(RequestEnum.CheckOrderInfoNew);
        httpPostRequestor = new HttpPostRequestor(SSLHttpClient.getSSLHttpClient(), requestEntity);
        String content = httpPostRequestor.request();
        System.out.println("content = " + content);
        return content;
    }
}

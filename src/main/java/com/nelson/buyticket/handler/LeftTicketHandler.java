package com.nelson.buyticket.handler;

import com.nelson.buyticket.httpclient.HttpGetRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;

/**
 *
 * @author Administrator
 */
public class LeftTicketHandler implements Handler {

    private HttpGetRequestor httpGetRequestor;

  
    @Override
    public String handler() {
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(), "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=queryLeftTicket&orderRequest.train_date=2013-02-01&orderRequest.from_station_telecode=SZQ&orderRequest.to_station_telecode=GGQ&orderRequest.train_no=&trainPassType=QB&trainClass=D%23&includeStudent=00&seatTypeAndNum=&orderRequest.start_time_str=00%3A00--24%3A00", "/query.json");
        return httpGetRequestor.request();
    }
}

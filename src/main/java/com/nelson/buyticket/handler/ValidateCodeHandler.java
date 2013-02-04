package com.nelson.buyticket.handler;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.httpclient.HttpGetRequestor;
import com.nelson.buyticket.httpclient.SSLHttpClient;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;

/**
 *
 * @author Administrator
 */
public class ValidateCodeHandler implements Handler {

    private final ParameterThreadLocal threadLocal;
    private HttpGetRequestor httpGetRequestor;
    private RequestEntity requestEntity;

    public ValidateCodeHandler(ParameterThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }
    public ValidateCodeHandler(RequestEntity requestEntity,ParameterThreadLocal threadLocal) {
        this.requestEntity = requestEntity;
        this.threadLocal = threadLocal;
    }

    @Override
    public String handler() {
        httpGetRequestor = new HttpGetRequestor(SSLHttpClient.getSSLHttpClient(),requestEntity);
        return httpGetRequestor.request();
    }
}

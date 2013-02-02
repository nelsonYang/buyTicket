package com.nelson.buyticket.handler;

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

    public ValidateCodeHandler(ParameterThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public String handler() {
        httpGetRequestor = new HttpGetRequestor(threadLocal.get("url"), threadLocal.get("jsonHeaderName"),SSLHttpClient.getSSLHttpClient(),threadLocal.get("imageUrl"));
        return httpGetRequestor.request();
    }
}

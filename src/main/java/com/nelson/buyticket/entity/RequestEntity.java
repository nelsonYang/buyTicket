package com.nelson.buyticket.entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class RequestEntity {
    private String url;
    private String method;
    private Map<String,String> headers = new HashMap<String,String>(10,1);
    private Map<String,String> parameters = new HashMap<String,String>(20,1);

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the headers
     */
    public Map<String,String> getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(Map<String,String> headers) {
        this.headers = headers;
    }

    /**
     * @return the parameters
     */
    public Map<String,String> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String,String> parameters) {
        this.parameters = parameters;
    }
    
}

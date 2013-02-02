package com.nelson.buyticket.threaLocal;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ParameterThreadLocal extends ThreadLocal{
    private final Map<String,String> paramterMap = new HashMap<String,String>(10,1);
    public ParameterThreadLocal(){
        super.set(paramterMap);
    }
    public void set(String key, String value){
        this.paramterMap.put(key, value);
    }
    public String get(String key){
       return this.paramterMap.get(key);
    }
}

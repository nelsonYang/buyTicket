package com.nelson.configuration;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.json.JsonParser;
import com.nelson.buyticket.requestenum.RequestEnum;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ConfigurationReader {

    private static final EnumMap<RequestEnum, RequestEntity> requestMap = new EnumMap<RequestEnum, RequestEntity>(RequestEnum.class);
    // private static final Map<String,String> configureMap = new HashMap<String,String>(10,1);
    private static Properties pro = new Properties();

    /**
     * @return the requestMap
     */
    public static EnumMap<RequestEnum, RequestEntity> getRequestMap() {
        return requestMap;
    }

    /**
     * @return the configureMap
     */
//    public static Map<String,String> getConfigureMap() {
//        return configureMap;
//    }
    public void register() {
        requestMap.put(RequestEnum.ConfirmPassengerInitNew, JsonParser.parseRequestJson("/ConfirmPassengerInitNew.json"));
        requestMap.put(RequestEnum.LoginActionNew, JsonParser.parseRequestJson("/LoginActionNew.json"));
        requestMap.put(RequestEnum.CheckOrderInfoNew, JsonParser.parseRequestJson("/CheckOrderInfoNew.json"));
        requestMap.put(RequestEnum.ConfirmSingleForQueueNew, JsonParser.parseRequestJson("/ConfirmSingleForQueueNew.json"));
        requestMap.put(RequestEnum.GetQueueCountNew, JsonParser.parseRequestJson("/GetQueueCountNew.json"));
        requestMap.put(RequestEnum.LoginSuggestNew, JsonParser.parseRequestJson("/LoginSuggestNew.json"));
        requestMap.put(RequestEnum.PassCodeActionNew, JsonParser.parseRequestJson("/PassCodeActionNew.json"));
        requestMap.put(RequestEnum.QueryLeftTicketNew, JsonParser.parseRequestJson("/QueryLeftTicketNew.json"));
        requestMap.put(RequestEnum.LoginPassCode, JsonParser.parseRequestJson("/LoginPassCode.json"));
    }

    public void readConfiguration() {
        InputStream is = ConfigurationReader.class.getResourceAsStream("/system.properties");
        try {
            pro.load(is);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Object getValue(String key) {
        return pro.get(key);
    }
}

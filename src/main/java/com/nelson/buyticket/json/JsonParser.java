package com.nelson.buyticket.json;

import com.nelson.buyticket.entity.RequestEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class JsonParser {

    public static Map<String, String> parseJson(String path, String arrayName) {
        Map<String, String> headerMap = new HashMap<String, String>(2, 1);
        InputStream is = null;
        try {
            is = JsonParser.class.getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder jsonBuilder = new StringBuilder(100);
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }
            String jsonStr = jsonBuilder.toString();
            System.out.println("json=" + jsonStr);
            JSONObject jb = JSONObject.fromObject(jsonStr);
            //取查询参数params,是个json数组
            JSONArray jsons = jb.getJSONArray(arrayName);
            int jsonLength = jsons.size();
            //对json数组进行循环
            headerMap = new HashMap<String, String>(jsonLength, 1);
            JSONObject tempJson;
            for (int i = 0; i < jsonLength; i++) {
                tempJson = JSONObject.fromObject(jsons.get(i));
                headerMap.put(tempJson.getString("name"), tempJson.getString("value"));
            }
        } catch (IOException ex) {
            Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return headerMap;
    }

    public static RequestEntity parseRequestJson(String path) {
        RequestEntity requestEntity = null;
        InputStream is = null;
        try {
            requestEntity = new RequestEntity();
            is = JsonParser.class.getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder jsonBuilder = new StringBuilder(100);
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }
            String jsonStr = jsonBuilder.toString();
            Logger.getLogger(JsonParser.class.getName()).log(Level.FINEST, jsonStr);
            JSONObject jsonObj = JSONObject.fromObject(jsonStr);
            JSONObject requestObj = jsonObj.getJSONObject("request");
            //todo
            String method = requestObj.getString("method");
            requestEntity.setMethod(method);
            String url = requestObj.getString("url");
            requestEntity.setUrl(url);
            JSONArray headerArray = requestObj.getJSONArray("headers");
            int jsonLength = headerArray.size();
            //对json数组进行循环
            Map<String, String> headerMap = new HashMap<String, String>(jsonLength, 1);
            JSONObject tempJson;
            for (int i = 0; i < jsonLength; i++) {
                tempJson = JSONObject.fromObject(headerArray.get(i));
                headerMap.put(tempJson.getString("name"), tempJson.getString("value"));
            }
            requestEntity.setHeaders(headerMap);
            //
           JSONObject postDataObj = requestObj.getJSONObject("postData");
           if (postDataObj != null && postDataObj.size() > 0) {
                JSONArray paramsArray = postDataObj.getJSONArray("params");
                //对json数组进行循环
                jsonLength = postDataObj.size();
                Map<String, String> paramsMap = new HashMap<String, String>(jsonLength, 1);
                for (int i = 0; i < jsonLength; i++) {
                    tempJson = JSONObject.fromObject(paramsArray.get(i));
                    paramsMap.put(tempJson.getString("name"), tempJson.getString("value"));
                }
                requestEntity.setParameters(paramsMap);
            }


        } catch (IOException ex) {
            Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return requestEntity;
    }
}

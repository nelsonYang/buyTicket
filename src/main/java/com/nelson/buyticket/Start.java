package com.nelson.buyticket;

import com.nelson.buyticket.entity.RequestEntity;
import com.nelson.buyticket.handler.*;
import com.nelson.buyticket.requestenum.RequestEnum;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;
import com.nelson.buyticket.view.AbstractView;
import com.nelson.buyticket.view.LoginView;
import com.nelson.buyticket.view.ReserveView;
import com.nelson.configuration.ConfigurationReader;
import java.util.EnumMap;

/**
 * Hello world!
 *
 */
public class Start {

    public static void main(String[] args) {
//        ParameterThreadLocal parameterThreadLocal = new ParameterThreadLocal();
//        Handler checkOrderInfoHanlder = new CheckOrderInfoHandler(parameterThreadLocal);
//        Handler getQueueCountHandler = new GetQueueCountHandler();
//        Handler bookTicketHandler = new BookTicketHanlder(parameterThreadLocal);
//        parameterThreadLocal.set("url", "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand");
//        parameterThreadLocal.set("jsonHeaderName", "/password.json");
//        parameterThreadLocal.set("imageUrl", "c:/test.jpg");
//        Handler validateCodeHandler = new ValidateCodeHandler(parameterThreadLocal);
//        AbstractView reserveView = new ReserveView(null, validateCodeHandler, parameterThreadLocal, checkOrderInfoHanlder, getQueueCountHandler, bookTicketHandler);
//
//        Handler loginHandler = new LoginHandler(parameterThreadLocal);
//        Handler loginSuggesionHandler = new LoginSuggesionHandler();
//        Handler tokenHandler = new TokenHandler();
//        Handler leftTicketHandler = new LeftTicketHandler();
//        AbstractView loginView = new LoginView(reserveView, validateCodeHandler, parameterThreadLocal, loginSuggesionHandler, loginHandler, leftTicketHandler, tokenHandler);
//        loginView.show();
         init();

    }
    
    public static void init(){
       ConfigurationReader cr = new ConfigurationReader();
       cr.register();
       EnumMap<RequestEnum,RequestEntity> requestMap = ConfigurationReader.getRequestMap();
       System.out.println(requestMap.size());
       cr.readConfiguration();
       System.out.println(cr.getValue("userName"));
    }
}

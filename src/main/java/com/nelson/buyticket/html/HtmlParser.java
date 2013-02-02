package com.nelson.buyticket.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class HtmlParser {

    public static String parseToken(String htmlContent) {
        String reg = "<div><input type=\"hidden\" name=\"org.apache.struts.taglib.html.TOKEN\" value=\"(.*?)\"></div>";
        String token = "";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(htmlContent);
        if (m.find()) {
            token = m.group(1);
            System.out.println("token =" + token);
        } else {
            System.out.println("token not found");
        }
        return token;
    }
}

package com.nelson.buyticket.html;

import com.nelson.buyticket.entity.TokenLeftTicketNumEntity;
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

    public static String parseLeftTicketNumber(String htmlContent) {
        String reg = "<input type=\"hidden\" name=\"leftTicketStr\" id=\"left_ticket\" value=\"(.*?)\" />";
        String leftTicketNum = "";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(htmlContent);
        if (m.find()) {
            leftTicketNum = m.group(1);
            System.out.println("leftTicketNum =" + leftTicketNum);
        } else {
            System.out.println("leftTicketNum not found");
        }
        return leftTicketNum;

    }

    public static TokenLeftTicketNumEntity parseTokenLeftTicketNumber(String htmlContent) {
        TokenLeftTicketNumEntity tokenLeftTicketNumEntity = new TokenLeftTicketNumEntity();
        String token = parseToken(htmlContent);
        String leftTicketNum = parseLeftTicketNumber(htmlContent);
        tokenLeftTicketNumEntity.setToken(token);
        tokenLeftTicketNumEntity.setLeftTicketNumber(leftTicketNum);
        return tokenLeftTicketNumEntity;
    }
}

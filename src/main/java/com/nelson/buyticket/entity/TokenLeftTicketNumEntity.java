package com.nelson.buyticket.entity;

/**
 *
 * @author Administrator
 */
public class TokenLeftTicketNumEntity {
    private String token;
    private String leftTicketNumber;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the leftTicketNumber
     */
    public String getLeftTicketNumber() {
        return leftTicketNumber;
    }

    /**
     * @param leftTicketNumber the leftTicketNumber to set
     */
    public void setLeftTicketNumber(String leftTicketNumber) {
        this.leftTicketNumber = leftTicketNumber;
    }
    public String toJson(){
        return "{\"token\":" + "\"" + token + "\",\"leftTicketNumber\":" + "\"" + leftTicketNumber  + "\"}";
    }
    
}

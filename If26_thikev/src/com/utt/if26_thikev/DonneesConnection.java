package com.utt.if26_thikev;
//Ceci est un putin de test de merde
public class DonneesConnection {

    boolean error;
    String token;
    static String urlRoot = "http://train.sandbox.eutech-ssii.com/messenger/"; 

    public boolean getError() {
        return error;
    }
    public void setError(Boolean Error) {
        error = Error;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String Token) {
        token = Token;
    }

}
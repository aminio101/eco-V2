package com.eco;

import com.eco.entitys.ErrorEntity;

import static com.eco.enums.InteractorResponse.SOCKET_TIMEOUT;
import static com.eco.enums.InteractorResponse.TIMEOUT;

public class PV {
    public static String TokenURL = "identity.";
    public static String URL = "185.252.29.12";
    public static String PROTOCOL = "http://";
    public static String token = "";
    public static String tokenPrefix = "Bearer ";
    public static String mainVersion = "0";
    public static String lasteVrsion = "302.8";
    public static String urlCheckVersion = "https://ecoiwm.ir/api/";

    public static boolean checkText(String text) {
        if (null == text)
            return false;
        else if (text.length() == 0 || text.equals(" ")) return false;
        else return true;
    }
    public static boolean checkTimeOutError(ErrorEntity errorEntity){
        if (errorEntity.getInteractorResponse() == SOCKET_TIMEOUT&& errorEntity.getInteractorResponse() == TIMEOUT)
            return true;
        return false;
    }
    public static String getImage(String image){
        return "http://185.252.29.12/storage/" + image;
    }
}

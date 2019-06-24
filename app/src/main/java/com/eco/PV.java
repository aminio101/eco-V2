package com.eco;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.LocationEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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


    public static LocationEntity locationEntity = new LocationEntity(0,0); // todo test

    public static int getHour(String time){
        return  Integer.parseInt( time.substring(9,11));

    }
    public static int getDayNumber(String date, int i) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd:HH");
        Date d = null;
        d = input.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }

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

package com.eco;

import com.eco.entitys.ErrorEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.RequestEntity;
import com.eco.entitys.RubbishEntity;
import com.eco.enums.RequstMode;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import saman.zamani.persiandate.PersianDate;

import static com.eco.enums.InteractorResponse.SOCKET_TIMEOUT;
import static com.eco.enums.InteractorResponse.TIMEOUT;

public class PV {
    public static String TokenURL = "identity.";
    public static String URL = "185.252.29.12";
    public static String PROTOCOL = "http://";
    public static String tokenPrefix = "Bearer ";
    public static String mainVersion = "0";
    public static String lasteVrsion = "302.8";
    public static String urlCheckVersion = "https://ecoiwm.ir/api/";
    public static RequestEntity requestEntity = new RequestEntity();
    public static ArrayList<RubbishEntity> list = new ArrayList<>();
    public static LocationEntity locationEntity = new LocationEntity(); // todo test
    public static RequstMode requstMode;
    public static int getHour(String time){
        return  Integer.parseInt( time.substring(9,11));

    }

    public static String numToDay(int day) {
        switch (day) {
            case 7:
                return "شنبه";

            case 1:
                return "یک شنبه";

            case 2:
                return "دوشنبه";

            case 3:
                return "سه شنبه";

            case 4:
                return "چهارشنبه";

            case 5:
                return "پنج شنبه";

            case 6:
                return "جمعه";
            default:
                return " ";
        }
    }

    /*    public static String getDate(String date, int i) {
//        SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd:HH");
//        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
//
//        Date d = null;
//
//        Calendar calendar = input.getCalendar();
////            d = input.parse(date);
//        calendar.add(Calendar.DAY_OF_YEAR, i);
//        d = calendar.getTime();
//
//
//        String formatted = output.format(d);
//        String day = Character.toString(formatted.charAt(0)) + Character.toString(formatted.charAt(1));
//        String month = Character.toString(formatted.charAt(3)) + Character.toString(formatted.charAt(4));
//        String year = Character.toString(formatted.charAt(6)) + Character.toString(formatted.charAt(7)) + Character.toString(formatted.charAt(8)) + Character.toString(formatted.charAt(9));
//
//        DateConverter converter = new DateConverter();
//        converter.gregorianToPersian(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
//
//        return converter.getYear() + "/" + converter.getMonth() + "/" + converter.getDay();
//    }*/
    public static int getDayNumber(String date, int i) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd:HH");
        Date d = null;
        d = input.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static Date stringToDate(String time) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        return input.parse(time);
    }

    public static String convert(String time) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        d = input.parse(time);
        PersianDate persianDate = new PersianDate(d);
        return persianDate.getShYear() + "/" + persianDate.getShMonth() + "/" + persianDate.getShDay();
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

    public static String addDay(String oldDate, int numberOfDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateFormat.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_YEAR, numberOfDays);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date(c.getTimeInMillis());
        String resultDate = dateFormat.format(newDate);
        return resultDate;
    }

    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    public static String getImage(String image){
        return "http://185.252.29.12/storage/" + image;
    }
}

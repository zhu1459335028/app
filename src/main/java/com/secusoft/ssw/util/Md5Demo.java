package com.secusoft.ssw.util;


import sun.security.provider.MD5;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Md5Demo {

    public static void main(String[] args) {
        String startTime = "1553147769";
        String endTime = "1553147769";
        Date date = new Date();


        String startDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.valueOf(startTime) * 1000));
        String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.valueOf(endTime) * 1000));

        System.out.println("startDate+" + startDate);
        System.out.println("endDate+" + endDate);


//        System.out.println("startDate+===>" + stampToDate(startTime));
//        System.out.println("endDate+===>" + stampToDate(endTime));
    }

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;

    }

}

package com.fadhlanhawali.alirin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConvertDate {

    public String convertTime(long time){

        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));


        return sdf.format(date);
    }

    public String convertHari(long time){
        Date date = new Date(time);

        SimpleDateFormat hari = new SimpleDateFormat("dd");
        hari.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        return hari.format(date).toString();
    }

    public String convertBulan(long time){
        Date date = new Date(time);

        SimpleDateFormat bulan = new SimpleDateFormat("MMM");
        bulan.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        return bulan.format(date).toString();
    }

    public String convertComplete(long time){
        Date date = new Date(time);

        SimpleDateFormat complete = new SimpleDateFormat("dd MMM yyy");
        complete.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        return complete.format(date).toString();
    }
}

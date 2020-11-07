package com.billy.footballmvvm.repositories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.billy.footballmvvm.views.activities.WebContentActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Presets {
    public static String leagueId = "4331";

    public static String nullable(String string){
        return string == null? "N/A" : string;
    }


    public static void toLink(Context context, String data){
        Intent intent = new Intent(context, WebContentActivity.class);
        intent.putExtra("link", data);
        context.startActivity(intent);
    }

    public static String getDate(){
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public static Integer getYear(){
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        return Integer.parseInt(timeStamp);
    }

    public static String converted(String time){
        String cleanTime = time.substring(0,time.length()-3);
        String spliceTime = cleanTime.substring(2);
        int hour = Integer.parseInt(time.substring(0,2));
        String newTime = "";
        if(hour > 12){
            hour -= 12;
            newTime = Integer.toString(hour) + spliceTime + "PM";
        }else if(hour == 12){
            newTime = cleanTime + "NN";
        }else if(hour == 0){
            newTime = cleanTime + "MN";
        }else{
            newTime = cleanTime + "AM";
        }
        return newTime ;
    }

//    public static String dateConvert(String time){
//        String date = time.substring(0,10);
//        String cleanTime = time.substring(11,15);
//        int hour = Integer.parseInt(cleanTime.substring(0,2));
//        String newTime = "";
//
//    }

}

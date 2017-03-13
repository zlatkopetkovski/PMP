package com.fikt.pmp.homework2;

import android.util.Log;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Zlatko on 3/10/2017.
 */

public class Clubs {
    private String City;
    private String Club;
    private int Num;

    //public String getCityClub() {
    //    return City;
    //}

    public Clubs(String num, String city, String club) {
        City = city;
        Club = club;
        try {
                Num = Integer.parseInt(num);
            }
         catch (Exception e) {
            Log.d("Zlatko", " integer error");
            //log the exception
        }

    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(String num) throws IOException {
        try {
            Num = Integer.parseInt(num);
        }
        catch (Exception e) {
            Log.d("Zlatko", " integer error");
        }
    }
}

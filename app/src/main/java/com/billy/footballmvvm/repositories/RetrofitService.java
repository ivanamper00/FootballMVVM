package com.billy.footballmvvm.repositories;

import java.util.SimpleTimeZone;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitService {

    public static <S> S retrofitService(Class<S> serviceClass, String URL){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }
    public static <S> S retrofitXMLService(Class<S> serviceClass, String URL){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

}


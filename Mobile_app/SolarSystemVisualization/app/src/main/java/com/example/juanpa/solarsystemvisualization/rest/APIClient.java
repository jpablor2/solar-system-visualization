package com.example.juanpa.solarsystemvisualization.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JuanPa on 21/05/2017.
 */

public class APIClient extends Object {

    public static final String BASE_URL="http://10.198.2.220:3000/server/";
    public static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

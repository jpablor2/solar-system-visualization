package com.example.juanpa.solarsystemvisualization.rest;


import com.example.juanpa.solarsystemvisualization.Models.Arrays;
import com.example.juanpa.solarsystemvisualization.Models.ArraysResponse;
import com.example.juanpa.solarsystemvisualization.Models.Inverters;
import com.example.juanpa.solarsystemvisualization.Models.InvertersResponse;
import com.example.juanpa.solarsystemvisualization.Models.Modules;
import com.example.juanpa.solarsystemvisualization.Models.ModulesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JuanPa on 21/05/2017.
 */

public interface MyApiEndpointInterface {



    @GET("getArreglos")
    Call<Arrays> getArreglos();

    @GET("getModulos")
    Call<Modules> getModulos();

    @GET("getInversores")
    Call<Inverters> getInversores();


}

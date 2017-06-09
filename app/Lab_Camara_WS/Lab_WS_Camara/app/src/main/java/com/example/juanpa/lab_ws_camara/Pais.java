package com.example.juanpa.lab_ws_camara;

import android.graphics.Bitmap;
//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuanPa on 02/06/2017.
 */

public class Pais {

    //@JsonProperty("name")
    private String name="";
    //@JsonProperty("alpha2_code")
    private String alpha2_code="";
    //@JsonProperty("alpha3_code")
    private String alpha3_code="";

    private ArrayList<Bitmap> images = new ArrayList<Bitmap>();


    public Pais() {
    }

    public Pais(String name, String alpha2_code, String alpha3_code) {
        this.name = name;
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2_code() {
        return alpha2_code;
    }

    public void setAlpha2_code(String alpha2_code) {
        this.alpha2_code = alpha2_code;
    }

    public String getAlpha3_code() {
        return alpha3_code;
    }

    public void setAlpha3_code(String alpha3_code) {
        this.alpha3_code = alpha3_code;
    }

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public void addImage(Bitmap image) {
        this.images.add(image);
    }
}

package com.example.juanpa.solarsystemvisualization.Models;

import android.graphics.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JuanPa on 23/05/2017.
 */

public class ArraysResponse {
    @SerializedName("arreglos")
    @Expose
    private List<Arrays> arreglos;
    @SerializedName("id_inversor")
    @Expose
    private String idInversor;
    @SerializedName("max_strings")
    @Expose
    private String maxStrings;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("micro")
    @Expose
    private String micro;

    public List<Arrays> getArreglos() {
        return arreglos;
    }

    public void setArreglos(List<Arrays> arreglos) {
        this.arreglos = arreglos;
    }

    public String getIdInversor() {
        return idInversor;
    }

    public void setIdInversor(String idInversor) {
        this.idInversor = idInversor;
    }

    public String getMaxStrings() {
        return maxStrings;
    }

    public void setMaxStrings(String maxStrings) {
        this.maxStrings = maxStrings;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMicro() {
        return micro;
    }

    public void setMicro(String micro) {
        this.micro = micro;
    }
}

package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by JuanPa on 21/05/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inverters {

    @SerializedName("_id")
    @Expose
    private String _id;
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

    public Inverters(String idInversor, String maxStrings, String modelo, String descripcion, String micro) {
        this._id = idInversor;
        this.maxStrings = maxStrings;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.micro = micro;
    }

    public String getIdInversor() {
        return _id;
    }

    public void setIdInversor(String idInversor) {
        this._id = idInversor;
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

package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by JuanPa on 21/05/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Arrays {
    @SerializedName("id_inversor")
    @Expose
    private String id_inversor;
    @SerializedName("max_strings")
    @Expose
    private String max_strings;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("micro")
    @Expose
    private String micro;

    public Arrays(String idInversor, String maxStrings, String modelo, String descripcion, String micro) {
        this.id_inversor = idInversor;
        this.max_strings = maxStrings;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.micro = micro;
    }

    public String getId_inversor() {
        return id_inversor;
    }

    public void setId_inversor(String id_inversor) {
        this.id_inversor = id_inversor;
    }

    public String getMax_strings() {
        return max_strings;
    }

    public void setMax_strings(String max_strings) {
        this.max_strings = max_strings;
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

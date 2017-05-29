package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by JuanPa on 21/05/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Arrays {
    @SerializedName("id_arreglo")
    @Expose
    private String idArreglo;
    @SerializedName("tipoConexion")
    @Expose
    private String tipoConexion;
    @SerializedName("nPaneles")
    @Expose
    private String nPaneles;
    @SerializedName("anguloInclinacion")
    @Expose
    private String anguloInclinacion;
    @SerializedName("anguloOrientacion")
    @Expose
    private String anguloOrientacion;

    /**
     * No args constructor for use in serialization
     *
     */
    public Arrays() {
    }


    public Arrays(String idArreglo, String tipoConexion, String nPaneles, String anguloInclinacion, String anguloOrientacion) {
        super();
        this.idArreglo = idArreglo;
        this.tipoConexion = tipoConexion;
        this.nPaneles = nPaneles;
        this.anguloInclinacion = anguloInclinacion;
        this.anguloOrientacion = anguloOrientacion;
    }

    public String getIdArreglo() {
        return idArreglo;
    }

    public void setIdArreglo(String idArreglo) {
        this.idArreglo = idArreglo;
    }

    public String getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    public String getNPaneles() {
        return nPaneles;
    }

    public void setNPaneles(String nPaneles) {
        this.nPaneles = nPaneles;
    }

    public String getAnguloInclinacion() {
        return anguloInclinacion;
    }

    public void setAnguloInclinacion(String anguloInclinacion) {
        this.anguloInclinacion = anguloInclinacion;
    }

    public String getAnguloOrientacion() {
        return anguloOrientacion;
    }

    public void setAnguloOrientacion(String anguloOrientacion) {
        this.anguloOrientacion = anguloOrientacion;
    }
}

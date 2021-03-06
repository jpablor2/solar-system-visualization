package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by JuanPa on 21/05/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Arrays {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("tipo_conexion")
    @Expose
    private String tipo_conexion;
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
        this._id = idArreglo;
        this.tipo_conexion = tipoConexion;
        this.nPaneles = nPaneles;
        this.anguloInclinacion = anguloInclinacion;
        this.anguloOrientacion = anguloOrientacion;
    }

    public String getIdArreglo() {
        return _id;
    }

    public void setIdArreglo(String idArreglo) {
        this._id = idArreglo;
    }

    public String getTipoConexion() {
        return tipo_conexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipo_conexion = tipoConexion;
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

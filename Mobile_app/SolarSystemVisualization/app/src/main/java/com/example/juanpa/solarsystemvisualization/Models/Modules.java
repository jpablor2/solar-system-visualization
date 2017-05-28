package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by JuanPa on 21/05/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modules {

    @SerializedName("id_modulo")
    @Expose
    private String idModulo;
    @SerializedName("id_inversor")
    @Expose
    private String idInversor;
    @SerializedName("id_arreglo")
    @Expose
    private String idArreglo;
    @SerializedName("p_stc")
    @Expose
    private String pStc;
    @SerializedName("p_noct")
    @Expose
    private String pNoct;
    @SerializedName("eficiencia")
    @Expose
    private String eficiencia;
    @SerializedName("fact_desemp")
    @Expose
    private String factDesemp;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Modules(String idModulo, String idInversor, String idArreglo, String pStc, String pNoct, String eficiencia, String factDesemp, String modelo, String descripcion) {
        this.idModulo = idModulo;
        this.idInversor = idInversor;
        this.idArreglo = idArreglo;
        this.pStc = pStc;
        this.pNoct = pNoct;
        this.eficiencia = eficiencia;
        this.factDesemp = factDesemp;
        this.modelo = modelo;
        this.descripcion = descripcion;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public String getIdInversor() {
        return idInversor;
    }

    public void setIdInversor(String idInversor) {
        this.idInversor = idInversor;
    }

    public String getIdArreglo() {
        return idArreglo;
    }

    public void setIdArreglo(String idArreglo) {
        this.idArreglo = idArreglo;
    }

    public String getPStc() {
        return pStc;
    }

    public void setPStc(String pStc) {
        this.pStc = pStc;
    }

    public String getPNoct() {
        return pNoct;
    }

    public void setPNoct(String pNoct) {
        this.pNoct = pNoct;
    }

    public String getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(String eficiencia) {
        this.eficiencia = eficiencia;
    }

    public String getFactDesemp() {
        return factDesemp;
    }

    public void setFactDesemp(String factDesemp) {
        this.factDesemp = factDesemp;
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
}

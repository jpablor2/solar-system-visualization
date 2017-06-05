package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by Gabriel on 4/6/2017.
 */

import com.example.juanpa.solarsystemvisualization.Database.Modulo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Conjunto {
    @SerializedName("id_arreglo")
    @Expose
    private int idArreglo;
    @SerializedName("tipo_conexion")
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
    @SerializedName("id_inversor")
    @Expose
    private int idInversor;
    @SerializedName("max_strings")
    @Expose
    private String maxStrings;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("descripcion_inv")
    @Expose
    private String descripcionInv;
    @SerializedName("micro")
    @Expose
    private String micro;
    @SerializedName("l_modulos")
    @Expose
    private List<Modules> lModulos = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Conjunto() {
    }

    /**
     *
     * @param nPaneles
     * @param maxStrings
     * @param micro
     * @param idInversor
     * @param tipoConexion
     * @param idArreglo
     * @param lModulos
     * @param descripcionInv
     * @param modelo
     * @param anguloInclinacion
     * @param anguloOrientacion
     */
    public Conjunto(int idArreglo, String tipoConexion, String nPaneles, String anguloInclinacion, String anguloOrientacion, int idInversor, String maxStrings, String modelo, String descripcionInv, String micro, List<Modules> lModulos) {
        super();
        this.idArreglo = idArreglo;
        this.tipoConexion = tipoConexion;
        this.nPaneles = nPaneles;
        this.anguloInclinacion = anguloInclinacion;
        this.anguloOrientacion = anguloOrientacion;
        this.idInversor = idInversor;
        this.maxStrings = maxStrings;
        this.modelo = modelo;
        this.descripcionInv = descripcionInv;
        this.micro = micro;
        this.lModulos = lModulos;
    }

    public int getIdArreglo() {
        return idArreglo;
    }

    public void setIdArreglo(int idArreglo) {
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

    public int getIdInversor() {
        return idInversor;
    }

    public void setIdInversor(int idInversor) {
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

    public String getDescripcionInv() {
        return descripcionInv;
    }

    public void setDescripcionInv(String descripcionInv) {
        this.descripcionInv = descripcionInv;
    }

    public String getMicro() {
        return micro;
    }

    public void setMicro(String micro) {
        this.micro = micro;
    }

    public List<Modules> getlModulos() {
        return lModulos;
    }

    public void setlModulos(List<Modules> lModulos) {
        this.lModulos = lModulos;
    }

}

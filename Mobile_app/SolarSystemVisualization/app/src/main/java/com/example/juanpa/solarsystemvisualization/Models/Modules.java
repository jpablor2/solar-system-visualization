package com.example.juanpa.solarsystemvisualization.Models;

/**
 * Created by JuanPa on 21/05/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Modules {

    @SerializedName("_id")
    @Expose
    private String _id;
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
        this._id = idModulo;
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
        return _id;
    }

    public void setIdModulo(String idModulo) {
        this._id = idModulo;
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

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {

            obj.put("_id",_id);
            obj.put("idInversor",idInversor);
            obj.put("idArreglo",idArreglo);
            obj.put("pStc",pStc);
            obj.put("pNoct",pNoct);
            obj.put("eficiencia",eficiencia);
            obj.put("factDesemp",factDesemp);
            obj.put("modelo",modelo);
            obj.put("descripcion",descripcion);
        } catch (JSONException e) {
            //trace("DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }
}

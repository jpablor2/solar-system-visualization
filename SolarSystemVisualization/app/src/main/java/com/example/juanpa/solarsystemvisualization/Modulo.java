package com.example.juanpa.solarsystemvisualization;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;


public class Modulo implements Parcelable{

    public static final String TIPO_SERIAL = "S";
    public static final String TIPO_PARALELO = "P";
    public static final String TIPO_MIXTO = "M";

    private String identificacion;
    private String id_inversor;
    private String id_arreglo;
    private int p_STC;
    private int p_NOCT;
    private double eficiencia;
    private double fact_desemp;
    private String modelo;
    private String descripcion;



    public Modulo() {
    }

    public Modulo(String identificacion, String id_inversor, String id_arreglo, int p_STC, int p_NOCT,
                  double eficiencia, double fact_desemp, String modelo, String descripcion) {
        this.identificacion = identificacion;
        this.id_inversor = id_inversor;
        this.id_arreglo = id_arreglo;
        this.p_STC = p_STC;
        this.p_NOCT = p_NOCT;
        this.eficiencia = eficiencia;
        this.fact_desemp = fact_desemp;
        this.modelo = modelo;
        this.descripcion = descripcion;
    }

    protected Modulo(Parcel in) {
        identificacion = in.readString();
        id_inversor = in.readString();
        id_arreglo = in.readString();
        p_STC = in.readInt();
        p_NOCT = in.readInt();
        eficiencia = in.readDouble();
        fact_desemp = in.readDouble();
        modelo = in.readString();
        descripcion = in.readString();

    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getId_inversor() {
        return id_inversor;
    }

    public void setId_inversor(String id_inversor) {
        this.id_inversor = id_inversor;
    }

    public String getId_arreglo() {
        return id_arreglo;
    }

    public void setId_arreglo(String id_arreglo) {
        this.id_arreglo = id_arreglo;
    }

    public int getP_STC() {
        return p_STC;
    }

    public void setP_STC(int p_STC) {
        this.p_STC = p_STC;
    }

    public int getP_NOCT() {
        return p_NOCT;
    }

    public void setP_NOCT(int p_NOCT) {
        this.p_NOCT = p_NOCT;
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    public double getFact_desemp() {
        return fact_desemp;
    }

    public void setFact_desemp(double fact_desemp) {
        this.fact_desemp = fact_desemp;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identificacion);
        dest.writeString(id_inversor);
        dest.writeString(id_arreglo);
        dest.writeInt(p_STC);
        dest.writeInt(p_NOCT);
        dest.writeDouble(eficiencia);
        dest.writeDouble(fact_desemp);
        dest.writeString(modelo);
        dest.writeString(descripcion);
    }

    public static final Creator<Modulo> CREATOR = new Creator<Modulo>() {
        @Override
        public Modulo createFromParcel(Parcel in) {
            return new Modulo(in);
        }

        @Override
        public Modulo[] newArray(int size) {
            return new Modulo[size];
        }
    };

    // Inserta un modulo en la base de datos
    public long insertar(Context context) {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, getIdentificacion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_INVERSOR, getId_inversor());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ARREGLO, getId_arreglo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_P_STC, getP_STC());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_P_NOCT, getP_NOCT());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_EFICIENCIA, getEficiencia());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FACT_DESEMP, getFact_desemp());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_MODULO, getModelo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_MODULO, getDescripcion());

        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_MODULO, null, values);
    }

    // Lee un modulo desde la base de datos
    public void leer (Context context, String identificacion){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_INVERSOR,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ARREGLO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_P_STC,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_P_NOCT,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_EFICIENCIA,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FACT_DESEMP,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_MODULO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_MODULO
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
        String[] selectionArgs = {identificacion};
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_MODULO, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        // recorrer los resultados y asignarlos a la clase // aca podria implementarse un ciclo si es necesario
        System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst() && cursor.getCount() > 0) {
            setIdentificacion(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry._ID)));
            setId_inversor(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_INVERSOR)));
            setId_arreglo(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ARREGLO)));
            setP_STC(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_P_STC)));
            setP_NOCT(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_P_NOCT)));
            setEficiencia(cursor.getDouble(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_EFICIENCIA)));
            setFact_desemp(cursor.getDouble(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_FACT_DESEMP)));
            setModelo(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_MODULO)));
            setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_MODULO)));
        }

    }
    // Elmina un modulo de la base de datos
    public void eliminar (Context context, String identificacion){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

        String[] selectionArgs = {identificacion};

        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_MODULO, selection, selectionArgs);
    }
    // Actualiza un modulo en la base de datos
    public int actualizar(Context context) {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_P_STC, getP_STC());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_P_NOCT, getP_NOCT());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_EFICIENCIA, getEficiencia());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FACT_DESEMP, getFact_desemp());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_MODULO, getModelo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_MODULO, getDescripcion());

        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

        String[] selectionArgs = {getIdentificacion()};

        return db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_MODULO, values, selection, selectionArgs);
    }
}

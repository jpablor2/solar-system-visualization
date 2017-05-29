package com.example.juanpa.solarsystemvisualization.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Arreglo implements Parcelable{

    public static final char TIPO_SERIAL = 'S';
    public static final char TIPO_PARALELO = 'P';
    public static final char TIPO_MIXTO = 'M';

    private String identificacion;
    private char tipo_conexion;
    private int n_paneles;
    private double angulo_inclinacion;
    private double angulo_orientacion;

    public Arreglo(String identificacion, char tipo_conexion, int n_paneles, double angulo_inclinacion, double angulo_orientacion) {
        this.identificacion = identificacion;
        this.tipo_conexion = tipo_conexion;
        this.n_paneles = n_paneles;
        this.angulo_inclinacion = angulo_inclinacion;
        this.angulo_orientacion = angulo_orientacion;
    }

    public Arreglo() {
    }

    protected Arreglo(Parcel in){
        identificacion = in.readString();
        tipo_conexion = in.readString().charAt(0);
        n_paneles = in.readInt();
        angulo_inclinacion = in.readDouble();
        angulo_orientacion = in.readDouble();
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public char getTipo_conexion() {
        return tipo_conexion;
    }

    public void setTipo_conexion(char tipo_conexion) {
        this.tipo_conexion = tipo_conexion;
    }

    public int getN_paneles() {
        return n_paneles;
    }

    public void setN_paneles(int n_paneles) {
        this.n_paneles = n_paneles;
    }

    public double getAngulo_inclinacion() {
        return angulo_inclinacion;
    }

    public void setAngulo_inclinacion(double angulo_inclinacion) {
        this.angulo_inclinacion = angulo_inclinacion;
    }

    public double getAngulo_orientacion() {
        return angulo_orientacion;
    }

    public void setAngulo_orientacion(double angulo_orientacion) {
        this.angulo_orientacion = angulo_orientacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identificacion);
        dest.writeString(String.valueOf(tipo_conexion));
        dest.writeInt(n_paneles);
        dest.writeDouble(angulo_inclinacion);
        dest.writeDouble(angulo_orientacion);
    }

    public static final Creator<Arreglo> CREATOR = new Creator<Arreglo>() {
        @Override
        public Arreglo createFromParcel(Parcel source) {
            return new Arreglo(source);
        }

        @Override
        public Arreglo[] newArray(int size) {
            return new Arreglo[size];
        }
    };

    //Inserta un arreglo a la base de datos
    public long insertar(Context context) {
        Log.d("FLAG","Entró en insertar");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        Log.d("FLAG","Creó helper");
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Log.d("FLAG","Obtuvo DB");
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, getIdentificacion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO_CONEXION, String.valueOf(getTipo_conexion()));
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_N_PANELES, getN_paneles());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_INCLIN, getAngulo_inclinacion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_ORIEN, getAngulo_orientacion());

        Log.d("FLAG","Insertó en BD");
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ARREGLO, null, values);

    }

    // Lee un arreglo desde la base de datos
    public void leer (Context context, String identificacion){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO_CONEXION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_N_PANELES,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_INCLIN,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_ORIEN
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
        String[] selectionArgs = {identificacion};

        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ARREGLO, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );


        System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst() && cursor.getCount() > 0) {
            setIdentificacion(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry._ID)));
            setTipo_conexion(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO_CONEXION)).charAt(0));
            setN_paneles(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_N_PANELES)));
            setAngulo_inclinacion(cursor.getDouble(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_INCLIN)));
            setAngulo_orientacion(cursor.getDouble(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_ORIEN)));
        }
    }

    // Elmina un arreglo de la base de datos
    public void eliminar (Context context, String identificacion){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

        String[] selectionArgs = {identificacion};

        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_ARREGLO, selection, selectionArgs);
    }

    // Actualiza un arreglo en la base de datos
    public int actualizar(Context context) {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO_CONEXION, String.valueOf(getTipo_conexion()));
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_N_PANELES, getN_paneles());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_INCLIN, getAngulo_inclinacion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANGULO_ORIEN, getAngulo_orientacion());

        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

        String[] selectionArgs = {getIdentificacion()};

        return db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_ARREGLO, values, selection, selectionArgs);
    }
}

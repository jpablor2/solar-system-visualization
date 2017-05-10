package com.example.juanpa.solarsystemvisualization;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public class Inversor implements Parcelable{

    private String identificacion;
    private int max_strings;
    private String modelo;
    private String descripcion;
    private boolean micro;

    public Inversor() {
    }

    public Inversor(String identificacion, int max_strings, String modelo, String descripcion, boolean micro) {
        this.identificacion = identificacion;
        this.max_strings = max_strings;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.micro = micro;
    }

    protected Inversor(Parcel in){
        identificacion = in.readString();
        max_strings = in.readInt();
        modelo = in.readString();
        descripcion = in.readString();
        micro =  (in.readInt() == 0) ? false : true;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getMax_strings() {
        return max_strings;
    }

    public void setMax_strings(int max_strings) {
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

    public boolean isMicro() {
        return micro;
    }

    public void setMicro(boolean micro) {
        this.micro = micro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identificacion);
        dest.writeInt(max_strings);
        dest.writeInt(micro ? 1 : 0);
        dest.writeString(modelo);
        dest.writeString(descripcion);
    }

    public static final Creator<Inversor> CREATOR = new Creator<Inversor>() {
        @Override
        public Inversor createFromParcel(Parcel source) {
            return new Inversor(source);
        }

        @Override
        public Inversor[] newArray(int size) {
            return new Inversor[size];
        }
    };

    //Inserta un inversor a la base de datos
    public long insertar(Context context) {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, getIdentificacion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MAX_STRINGS, getMax_strings());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_INVERSOR, getModelo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_INVERSOR, getDescripcion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MICRO_INVERSOR, isMicro());

        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_INVERSOR, null, values);
    }

    // Lee un modulo desde la base de datos
    public void leer (Context context, String identificacion){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MAX_STRINGS,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_INVERSOR,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_INVERSOR,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MICRO_INVERSOR
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
        String[] selectionArgs = {identificacion};

        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_INVERSOR, // tabla
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
            setMax_strings(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_MAX_STRINGS)));
            setMicro(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_MICRO_INVERSOR)) > 0);
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

        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_INVERSOR, selection, selectionArgs);
    }

    // Actualiza un modulo en la base de datos
    public int actualizar(Context context) {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MAX_STRINGS, getMax_strings());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MODELO_INVERSOR, getModelo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_INVERSOR, getDescripcion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MICRO_INVERSOR, isMicro());

        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

        String[] selectionArgs = {getIdentificacion()};

        return db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_MODULO, values, selection, selectionArgs);
    }

}

package com.example.juanpa.solarsystemvisualization.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.juanpa.solarsystemvisualization.Database.DataBaseContract;


public class DataBaseHelper extends SQLiteOpenHelper {
    // debemos incrementar la version de la base de datos
    public static final int DATABASE_VERSION = 2;
    // Nombre de la base de datos
    public static final String DATABASE_NAME = "AndroidStorage.db";

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear la base de datos de la app
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_MODULO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_INVERSOR);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_ARREGLO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Administracion de actualizaciones

        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_MODULO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_INVERSOR);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_ARREGLO);
        onCreate(sqLiteDatabase);
    }
}

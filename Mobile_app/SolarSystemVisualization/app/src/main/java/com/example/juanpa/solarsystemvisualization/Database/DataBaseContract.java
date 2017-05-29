package com.example.juanpa.solarsystemvisualization.Database;

import android.provider.BaseColumns;


public class DataBaseContract {
    // Implementa la interfaz BaseColumns para heredar campos estandar del marco de Android _ID

    public static class DataBaseEntry implements BaseColumns {

        // Clase Modulo

        public static final String TABLE_NAME_MODULO = "Modulo";

        // private String ID_modulo; Utilizamos DataBaseEntry._ID de BaseColumns

        public static final String COLUMN_NAME_ID_INVERSOR = "id_inversor";

        public static final String COLUMN_NAME_ID_ARREGLO = "id_arreglo";

        public static final String COLUMN_NAME_P_STC = "p_stc";

        public static final String COLUMN_NAME_P_NOCT = "p_noct";

        public static final String COLUMN_NAME_EFICIENCIA = "eficiencia";

        public static final String COLUMN_NAME_FACT_DESEMP = "fact_desemp";

        public static final String COLUMN_NAME_MODELO_MODULO = "modelo";

        public static final String COLUMN_NAME_DESCRIPCION_MODULO = "descripcion";


        // Clase Inversor

        public static final String TABLE_NAME_INVERSOR = "Inversor";

        public static final String COLUMN_NAME_MAX_STRINGS = "max_strings";

        public static final String COLUMN_NAME_MODELO_INVERSOR = "modelo";

        public static final String COLUMN_NAME_DESCRIPCION_INVERSOR = "descripcion";

        public static final String COLUMN_NAME_MICRO_INVERSOR = "micro";


        // Clase Arreglo

        public static final String TABLE_NAME_ARREGLO = "Arreglo";

        public static final String COLUMN_NAME_TIPO_CONEXION = "tipoConexion";

        public static final String COLUMN_NAME_N_PANELES = "nPaneles";

        public static final String COLUMN_NAME_ANGULO_INCLIN = "anguloInclinacion";

        public static final String COLUMN_NAME_ANGULO_ORIEN = "anguloOrientacion";

    }

// Construir las tablas de la base de datos

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String DOUBLE_TYPE = " DOUBLE";

    private static final String BOOL_TYPE = " BOOL";

    private static final String CHAR_TYPE = " CHAR";

    private static final String COMMA_SEP = ",";


    // Creacion de tablas Modulo, Inversor, Arreglo

    public static final String SQL_CREATE_MODULO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_MODULO + " (" +

                    DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," +

                    DataBaseEntry.COLUMN_NAME_P_STC + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_P_NOCT + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_EFICIENCIA + DOUBLE_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_FACT_DESEMP + DOUBLE_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_MODELO_MODULO + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_DESCRIPCION_MODULO + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_ID_INVERSOR+ TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_ID_ARREGLO + TEXT_TYPE + COMMA_SEP +

                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_ID_INVERSOR + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_INVERSOR + "(" + DataBaseEntry._ID +")" + COMMA_SEP +

                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_ID_ARREGLO + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_ARREGLO + "(" + DataBaseEntry._ID +"))";

    public static final String SQL_DELETE_MODULO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_MODULO;


    public static final String SQL_CREATE_INVERSOR =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_INVERSOR + " (" +

                    DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," +

                    DataBaseEntry.COLUMN_NAME_MAX_STRINGS + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_MODELO_INVERSOR + TEXT_TYPE+ COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_DESCRIPCION_INVERSOR + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_MICRO_INVERSOR + BOOL_TYPE +

                    ")";

    public static final String SQL_DELETE_INVERSOR =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_INVERSOR;


    public static final String SQL_CREATE_ARREGLO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ARREGLO + " (" +

                    DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," +

                    DataBaseEntry.COLUMN_NAME_TIPO_CONEXION + CHAR_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_N_PANELES + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_ANGULO_INCLIN + DOUBLE_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_ANGULO_ORIEN + DOUBLE_TYPE +

                    ")";


    public static final String SQL_DELETE_ARREGLO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ARREGLO;
}

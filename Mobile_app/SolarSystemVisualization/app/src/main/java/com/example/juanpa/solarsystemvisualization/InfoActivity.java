package com.example.juanpa.solarsystemvisualization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.juanpa.solarsystemvisualization.Database.Arreglo;
import com.example.juanpa.solarsystemvisualization.Database.Inversor;
import com.example.juanpa.solarsystemvisualization.Database.Modulo;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        Arreglo arreglo = new Arreglo("A01",Arreglo.TIPO_PARALELO,12,12,90);
        long newRowIdA = arreglo.insertar(getApplicationContext());

        Inversor inversor = new Inversor("I01",12,"Sony Boy","ENPHASE",false);
        long newRowIdI = inversor.insertar(getApplicationContext());

        Modulo modulo = new Modulo("M01","I01","A01",250,183,14.91,80,"SW250MONO","Solar World");
        long newRowIdM = modulo.insertar(getApplicationContext());

        Modulo modulo2 = new Modulo();

        modulo2.leer(getApplicationContext(), "M01");

        if (modulo2.getIdentificacion() != null) {

            Toast.makeText(getApplicationContext(), "Modulo: " +
                    modulo2.getIdentificacion() +
                    " Arreglo: " + modulo2.getId_arreglo() +
                    " Inversor asociado: " + modulo2.getId_inversor() +
                    " Potencia STC: " + modulo2.getP_STC() +
                    " Potencia NOTC: " + modulo2.getP_NOCT() +
                    " Eficiencia: " + modulo2.getEficiencia() +
                    " Factor de rendimiento: " + modulo2.getFact_desemp() +
                    " Modelo: " + modulo2.getModelo() +
                    " Descripción: " + modulo2.getDescripcion(), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay datos para: " + "M01", Toast.LENGTH_LONG).show();
        }

        Arreglo arreglo1 = new Arreglo();

        arreglo1.leer(getApplicationContext(), "A01");

        if (arreglo1.getIdentificacion() != null) {

            Toast.makeText(getApplicationContext(), "Arreglo: " +
                    arreglo1.getIdentificacion() +
                    " Tipo: " + arreglo1.getTipo_conexion() +
                    " Numero de paneles: " + arreglo1.getN_paneles() +
                    " Ángulo de inclinación : " + arreglo1.getAngulo_inclinacion() +
                    " Ángulo de orientación : " + arreglo1.getAngulo_orientacion(), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay datos para: " + "A01", Toast.LENGTH_LONG).show();
        }

        Inversor inversor1 = new Inversor();

        inversor1.leer(getApplicationContext(), "I01");

        if (inversor1.getIdentificacion() != null) {


            Toast.makeText(getApplicationContext(), "Inversor: " +
                    inversor1.getIdentificacion() +
                    " Máximo de arreglos: " + inversor1.getMax_strings() +
                    " Modelo: " + inversor1.getModelo() +
                    " Descripción : " + arreglo1.getAngulo_inclinacion() +
                    " Microinversor : " + (inversor1.isMicro() ? "Sí" : "No"), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay datos para: " + "I01", Toast.LENGTH_LONG).show();
        }

    }
}

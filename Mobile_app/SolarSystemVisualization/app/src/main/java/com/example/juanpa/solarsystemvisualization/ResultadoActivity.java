package com.example.juanpa.solarsystemvisualization;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.byga1402.solar_panel.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
    }

    public void login(View view){
        //Intent login_intent = new Intent(this, InfoActivity.class);
        //startActivity(login_intent);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){

            ArrayList<String> matchesText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Intent intent = new Intent(this, UnityPlayerActivity.class);
            Bundle info = getIntent().getExtras();
            Bundle bundle_unity = new Bundle();
            String infoModule = "";
            try {
                JSONArray lModules = new JSONArray(info.getString("lModules"));
                for(int i = 0; i<lModules.length(); i++){
                    JSONObject obj = lModules.getJSONObject(i);
                    if(obj.getString("_id").equals(in(matchesText))){
                        infoModule += "P. STC: "+obj.getString("pStc")+"\n"+
                                "P. NOCT: "+obj.getString("pNoct")+"\n"+
                                "Eficiencia: "+obj.getString("eficiencia")+"\n"+
                                "PF: "+obj.getString("factDesemp")+"\n"+
                                "Modelo: "+obj.getString("modelo")+"\n"+
                                "Descripción: "+obj.getString("descripcion")+"\n";
                    }
                }
            }catch (JSONException E){

            }
            bundle_unity.putString("Left","Módulo: "+in(matchesText)+"\n\n"+infoModule);
            bundle_unity.putString("Right",info.getString("idArreglo")+"\n"+info.getString("tipo")+
                    info.getString("nPaneles")+
                    info.getString("orientacion")+
                    info.getString("inclinacion")+"\n"+
                    info.getString("idInversor")+
                    info.getString("maxStrings")+
                    info.getString("modelo")+
                    info.getString("descripcionInv")+
                    info.getString("micro"));
            intent.putExtras(bundle_unity);
            //Toast.makeText(this,in(matchesText),Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    public String in(ArrayList<String> l){
        ArrayList<String> lista = new ArrayList<String>();
        for(int i = 0; i<13; i++){
            if (l.contains(Integer.toString(i))){
                return Integer.toString(i);
            }
        }
        return "NaN";
    }
}

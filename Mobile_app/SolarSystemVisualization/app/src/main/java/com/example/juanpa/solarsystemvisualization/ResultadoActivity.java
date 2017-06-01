package com.example.juanpa.solarsystemvisualization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.byga1402.solar_panel.*;

public class ResultadoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
    }

    public void login(View view){
        //Intent login_intent = new Intent(this, InfoActivity.class);
        //startActivity(login_intent);

        Intent intent = new Intent(this, UnityPlayerActivity.class);
        Bundle data = getIntent().getExtras();
        Bundle bundle_unity = new Bundle();
        bundle_unity.putString("Left","Modelo:");
        bundle_unity.putString("Right","Arreglo:\n"+data.getString("tipo")+
                data.getString("nPaneles")+
                data.getString("orientacion")+
                data.getString("inclinacion"));
        intent.putExtras(bundle_unity);
        startActivity(intent);
    }
}

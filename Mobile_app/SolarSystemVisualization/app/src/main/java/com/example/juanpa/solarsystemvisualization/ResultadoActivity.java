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
        Bundle bundle = new Bundle();
        bundle.putString("Left","Texto de la izquierda"+System.getProperty("line.separator")+"Otra cosa");
        bundle.putString("Right","Right Text");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

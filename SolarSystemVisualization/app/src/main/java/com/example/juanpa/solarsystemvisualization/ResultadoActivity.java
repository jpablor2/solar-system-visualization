package com.example.juanpa.solarsystemvisualization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResultadoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
    }

    public void login(View view){
        Intent login_intent = new Intent(this, InfoActivity.class);
        startActivity(login_intent);
    }
}

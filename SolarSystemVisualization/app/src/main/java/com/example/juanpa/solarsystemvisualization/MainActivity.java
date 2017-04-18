package com.example.juanpa.solarsystemvisualization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        for(int x=0;x<3;x++) {
            ImageView image = new ImageView(MainActivity.this);
            image.setBackgroundResource(R.drawable.images1);
            linearLayout1.addView(image);
        }
    }
}

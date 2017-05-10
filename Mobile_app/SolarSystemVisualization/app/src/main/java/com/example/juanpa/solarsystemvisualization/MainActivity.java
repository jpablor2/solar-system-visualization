package com.example.juanpa.solarsystemvisualization;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
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

        /*LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        for(int x=0;x<1;x++) {
            ImageView image = new ImageView(MainActivity.this);

            image.setBackgroundResource(R.drawable.images1);

            linearLayout1.addView(image);
        }*/
    }

    public void login(View view){
        Intent login_intent = new Intent(this, HomeActivity.class);
        startActivity(login_intent);
    }
}

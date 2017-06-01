package com.example.juanpa.solarsystemvisualization;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
//import android.telecom.Call;
import android.util.Config;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.juanpa.solarsystemvisualization.Models.Arrays;
import com.example.juanpa.solarsystemvisualization.Models.ArraysResponse;
import com.example.juanpa.solarsystemvisualization.Models.Inverters;
import com.example.juanpa.solarsystemvisualization.rest.*;

import java.util.List;

import static com.example.juanpa.solarsystemvisualization.rest.APIClient.retrofit;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getPermissionToUseCamera();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    private static final int USE_CAMERA_PERMISSIONS_REQUEST = 1;

    public void getPermissionToUseCamera() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA)) {

            }

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    USE_CAMERA_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == USE_CAMERA_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Use camera permission granted", Toast.LENGTH_SHORT).show();
            } else {

                boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);

                if (showRationale) {
                    // do something here to handle degraded mode
                } else {
                    Toast.makeText(this, "Use camera permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    public void login(View view){
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            //showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                //toast.show();

                APIClient APIClient = new APIClient();

                MyApiEndpointInterface apiService = APIClient.getClient().create(MyApiEndpointInterface.class);
                String id_arreglo=contents;
                Toast.makeText(this,"Request: "+id_arreglo, Toast.LENGTH_LONG).show();
                Call<Arrays> call = apiService.getArreglo(id_arreglo);
                call.enqueue(new Callback<Arrays>() {
                    @Override
                    public void onResponse(Call<Arrays> call, Response<Arrays> response) {

                        response.body();
                        /*String statusCode = String.valueOf(response.code());
                        List<Arrays> arreglos= (List<Arrays>) response.body();*/
                        if(response.body()!=null) {
                            String descripcion = response.body().getAnguloOrientacion();
                            Toast.makeText(getApplicationContext(),"Response: "+ descripcion, Toast.LENGTH_LONG).show();
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "NUUUULP"   , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                //call

                Intent login_intent = new Intent(this, ResultadoActivity.class);
                startActivity(login_intent);
            }
        }
    }
}

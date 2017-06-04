package com.example.gabriel.lab4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_REST = "http://services.groupkt.com/country/get/all";
    private TextView mDataText;


    List<Pais> model=new ArrayList<Pais>();
    CountryAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list=(ListView)findViewById(R.id.paises);


        mDataText = (TextView) findViewById(R.id.mDataText);
        adapter = new CountryAdapter();
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(onListLongClick);
        list.setOnItemClickListener(onlistClick);
        servicioREST();

    }

    private void servicioREST() {
        mDataText.setText("/*/ Llamada a web service REST /*/ \n");
        //
        mDataText.setText("Recuperando datos de: "+URL_REST);
        // Tarea AsyncTask para ejecutar la solicitud
        new TaskServicioREST().execute(URL_REST);
    }

    //**********************************************************************************************
    // Clase para la tarea asincronica de Gson en Servicio REST
    private class TaskServicioREST extends AsyncTask<String, Void, String> {
        // La tarea se ejecuta en un thread tomando como parametro el eviado en
        //   AsyncTask.execute()
        @Override
        protected String doInBackground(String... urls) {

            return loadContentFromNetwork(urls[0]);
        }

        // El resultado de la tarea tiene el archivo gson el cual mostramos
        protected void onPostExecute(String result) {
            JSONObject js = null;
            try {
                js = new JSONObject(result);
                JSONObject head = null;
                head = js.getJSONObject("RestResponse");
                JSONArray ar = null;
                ar = head.getJSONArray("result");
                for (int x = 0; x < ar.length(); x++) {
                    String name = ar.getJSONObject(x).getString("name");
                    String alpha2_code = ar.getJSONObject(x).getString("alpha2_code");
                    String alpha3_code = ar.getJSONObject(x).getString("alpha3_code");
                    Pais pais = new Pais(name, alpha2_code, alpha3_code);
                    adapter.add(pais);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // metodo para bajar el contenido
        private String loadContentFromNetwork(String url) {
            try {
                InputStream mInputStream = (InputStream) new URL(url).getContent();
                InputStreamReader mInputStreamReader = new InputStreamReader(mInputStream);
                BufferedReader responseBuffer = new BufferedReader(mInputStreamReader);
                StringBuilder strBuilder = new StringBuilder();
                String line = null;
                while ((line = responseBuffer.readLine()) != null) {
                    strBuilder.append(line);
                }

                return strBuilder.toString();

            } catch (Exception e) {
                //Log.v(TAG_IMG, e.getMessage());
            }
            return null;
        }
    }

    class CountryAdapter extends ArrayAdapter<Pais> {

        CountryAdapter() {
            super(MainActivity.this, R.layout.row, model);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            CountryHolder holder=null;
            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.row, parent,false);
                holder=new CountryHolder(row);
                row.setTag(holder);
            }
            else{
                holder=(CountryHolder) row.getTag();
            }
            holder.populateFrom(model.get(position));
            //Hay que modificar el model
            return (row);
        }
    }

    static class CountryHolder{
        private TextView name=null;
        private TextView alpha2_code=null;
        private TextView alpha3_code=null;
        CountryHolder(View row){
            name=(TextView)row.findViewById(R.id.name);
            alpha2_code=(TextView)row.findViewById(R.id.alpha2_code);
            alpha3_code=(TextView) row.findViewById(R.id.alpha3_code);
        }
        void populateFrom(Pais r){
            name.setText(r.getName());
            alpha2_code.setText(r.getAlpha2_code());
            alpha3_code.setText(r.getAlpha3_code());
        }
    }

    private static final int IMAGE_CAPTURE = 101;

    private int pos = 0;
    private AdapterView.OnItemLongClickListener onListLongClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pos = position;
            startActivityForResult(intent , IMAGE_CAPTURE);
            return false;
        }
    };

    private AdapterView.OnItemClickListener onlistClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, CountryActivity.class);

            Pais pais = adapter.getItem(position);

            Bundle bundle = new Bundle();
            bundle.putString("pais",pais.getName());
            bundle.putParcelableArrayList("images", pais.getImages());
            intent.putExtras(bundle);

            startActivity(intent);
        }
    };

    protected void onActivityResult(int requestCode ,
                                    int resultCode , Intent data) {

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        //Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Android/"+"foto.jpg");
        Pais pais = adapter.getItem(pos);
        pais.addImage(imageBitmap);

        Intent intent = new Intent(MainActivity.this, CountryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("pais",pais.getName());
        bundle.putParcelableArrayList("images", pais.getImages());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

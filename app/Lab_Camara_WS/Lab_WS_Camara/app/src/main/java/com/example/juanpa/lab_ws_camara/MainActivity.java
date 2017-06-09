package com.example.juanpa.lab_ws_camara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {


    //Servicios REST
    private static final String URL_REST = "http://services.groupkt.com/country/get/all ";

    List<Pais> model=new ArrayList<Pais>();
    InfoPaisAdapter adapter=null;
    public static final String TAG_IMG = "IMG";
    private TextView mDataText;
    private Button btn_llamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataText=(TextView) findViewById(R.id.mDataText);
        btn_llamar=(Button)findViewById(R.id.btn_llamar);


        ListView list=(ListView)findViewById(R.id.paises);
        mDataText = (TextView) findViewById(R.id.mDataText);
        adapter = new InfoPaisAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(onlistClick);
        Pais r=new Pais();
        r.setName("Costa Rica");
        r.setAlpha2_code("CR");
        r.setAlpha3_code("CRI");
        adapter.add(r);

        servicioREST();


        btn_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"si sirve Cris",LENGTH_LONG).show();
                servicioREST();

            }
        });
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*String url = "http://www.nacion.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);*/
            Intent i = new Intent(getApplicationContext(),InfoPais.class);
            startActivity(i);


            Toast.makeText(MainActivity.this, "Haciendo algo con la lista", Toast.LENGTH_SHORT).show();
        }
    };

    private void servicioREST() {

        //Toast.makeText(this,"estoy en rest",Toast.LENGTH_LONG).show();
        mDataText.setText("/*/ Llamada a web service REST /*/ \n");
        //
        mDataText.setText(URL_REST);
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
            // tomanos el parámetro del execute() y bajamos el contenido
            return loadContentFromNetwork(urls[0]);
        }

        // El resultado de la tarea tiene el archivo gson el cual mostramos
        protected void onPostExecute(String result) {
            //mDataText.append("\n \n" + result);
            //Toast.makeText(getApplicationContext(),result,LENGTH_LONG).show();
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
                //
                //*Gson mJson = new Gson();
                //*mJson.fromJson(strBuilder.toString(),);//,YourClass.class);
                //
                //*return mJson.toString(); //strBuilder.toString();
                //Toast.makeText(getApplicationContext(),strBuilder.toString(),LENGTH_LONG).show();
                return strBuilder.toString();

            } catch (Exception e) {
                Log.v(TAG_IMG, e.getMessage());
            }
            return null;
        }
    }
    class InfoPaisAdapter extends ArrayAdapter<Pais> {

        InfoPaisAdapter() {
            super(MainActivity.this, R.layout.row, model);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            InfoPais holder=null;
            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.row, parent,false);
                holder=new InfoPais(row);
                row.setTag(holder);
            }
            else{
                holder=(InfoPais) row.getTag();
            }
            holder.populateFrom(model.get(position));
            //Hay que modificar el model
            return (row);
        }
    }

    static class InfoPais{
        private TextView name=null;
        private TextView alpha2_code=null;
        private TextView alpha3_code=null;
        InfoPais(View row){
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
            Intent intent = new Intent(MainActivity.this, InfoPais.class);

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

        Intent intent = new Intent(MainActivity.this, InfoPais.class);
        Bundle bundle = new Bundle();
        bundle.putString("pais",pais.getName());
        bundle.putParcelableArrayList("images", pais.getImages());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

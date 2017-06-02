package com.example.gabriel.lab4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

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
        Pais r=new Pais();
        r.setName("Costa Rica");
        r.setAlpha2_code("CR");
        r.setAlpha3_code("CRI");
        adapter.add(r);
        servicioREST();
    }

    private void servicioREST() {
        mDataText.setText("/*/ Llamada a web service REST /*/ \n");
        //
        mDataText.setText(URL_REST);
        // Tarea AsyncTask para ejecutar la solicitud
        new TaskServicioREST().execute(URL_REST);
    }

    //**********************************************************************************************
    // Clase para la tarea asincronica de Gson en Servicio REST
    private class TaskServicioREST extends AsyncTask<String, Void, Response> {
        // La tarea se ejecuta en un thread tomando como parametro el eviado en
        //   AsyncTask.execute()
        @Override
        protected Response doInBackground(String... urls) {
            try {
                final String url =
                        URL_REST;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add( new
                        MappingJackson2HttpMessageConverter());
                Response pais = restTemplate.getForObject(url ,
                        Response.class ) ;
                return pais;
            } catch (Exception e) {
                Log.e(" MainActivity ", e.getMessage(), e);
            }
            return null ;
            //return loadContentFromNetwork(urls[0]);
        }

        // El resultado de la tarea tiene el archivo gson el cual mostramos
        protected void onPostExecute(Response result) {
            //TextView mDataText = (TextView) findViewById(R.id.mDataText);
            //String msg = result.getName();
            mDataText.append("\n\n" + result);

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

                Gson mJson = new Gson();
                mJson.fromJson(strBuilder.toString(),Response.class);//,YourClass.class);

                return mJson.toString(); //strBuilder.toString();
                //return strBuilder.toString();

            } catch (Exception e) {
                //Log.v(TAG_IMG, e.getMessage());
            }
            return null;
        }
    }

    class Response{
        private Result result;

        public Response(Result result) {
            this.result = result;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    class Result{
        private ArrayList<String> messages;
        private ArrayList<Pais> result;

        public Result(ArrayList<String> messages, ArrayList<Pais> result) {
            this.messages = messages;
            this.result = result;
        }

        public ArrayList<String> getMessages() {
            return messages;
        }

        public void setMessages(ArrayList<String> messages) {
            this.messages = messages;
        }

        public ArrayList<Pais> getResult() {
            return result;
        }

        public void setResult(ArrayList<Pais> result) {
            this.result = result;
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
}

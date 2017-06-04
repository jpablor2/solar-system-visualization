package com.example.gabriel.lab4;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.gabriel.lab4.R.id.mImageView;

public class CountryActivity extends AppCompatActivity {

    List<Bitmap> model=new ArrayList<Bitmap>();
    ImageAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        TextView name = (TextView) findViewById(R.id.nombre);
        ListView list=(ListView)findViewById(R.id.mImageView);

        name.setText(getIntent().getStringExtra("pais"));
        model = getIntent().getParcelableArrayListExtra("images");
        adapter = new ImageAdapter();

        //ArrayList<Bitmap> images = getIntent().getParcelableArrayListExtra("images");
        //ArrayAdapter<Bitmap> imgs = new ArrayAdapter<Bitmap>(this,android.R.layout.,images);
        list.setAdapter(adapter);

        /*for(int i = 0; i<images.size(); i++){
            adapter.add(images.get(i));
        }*/
    }

    class ImageAdapter extends ArrayAdapter<Bitmap> {

        ImageAdapter() {
            super(CountryActivity.this, R.layout.thumbnail, model);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View thumbnail=convertView;
            ImageHolder holder=null;
            if(thumbnail==null){
                LayoutInflater inflater=getLayoutInflater();
                thumbnail=inflater.inflate(R.layout.thumbnail, parent,false);
                holder=new ImageHolder(thumbnail);
                thumbnail.setTag(holder);
            }
            else{
                holder=(ImageHolder) thumbnail.getTag();
            }
            holder.populateFrom(model.get(position));
            //Hay que modificar el model
            return (thumbnail);
        }
    }

    static class ImageHolder{
        private ImageView photo=null;

        ImageHolder(View row){
            photo=(ImageView) row.findViewById(R.id.photo);
        }
        void populateFrom(Bitmap r){
            photo.setImageBitmap(r);

        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };
}

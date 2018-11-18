package com.example.felipe.tareaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Imagen extends AppCompatActivity {

    private static final String TAG = Mensaje.class.getName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //El dataset de tipo Photo
    private ArrayList<Photo> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        //Instanciamos el RecyclerView del activity_main layout y lo conectamos con la MainActivity
        mRecyclerView = (RecyclerView) findViewById(R.id.vista_imagen);

        // Si se sabe que la cantidad de items de la lista es siempre la misma y esta no cambiará
        // entonces podemos hacer uso de la sigiente propidad para mejorar el
        // Performance del RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Instanciamos un linear layout manager para setearlo en el RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Cargamos los datos en el dataset
        loadPhotosFromWeb();
    }


    private void refreshDataset() {

        if (mRecyclerView == null)
            return;

        if (mAdapter == null) {
            mAdapter = new ListAdapter(this, myDataset);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void loadPhotosFromWeb() {
        //Hacemos uso de Volley para consumir el End-point
        myDataset = new ArrayList<Photo>();

        //Definimos un String con la URL del End-point
        String url = "https://jsonplaceholder.typicode.com/photos";

        //Instanciamos un objeto RequestQueue el cual se encarga de gestionar la cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(this);

        //Armamos una peticion de tipo JSONArray por que es un JsonArray lo que obtendremos como resultado
        JsonArrayRequest aRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse");
                        //Obtenemos un JSONArray como respuesta
                        if (response != null && response.length() > 0){
                            //Iteramos son el JSONArray
                            for (int i=0; i <response.length(); i++){
                                try {
                                    JSONObject p = (JSONObject) response.get(i);
                                    if (p != null){
                                        //Armamos un objeto Photo con el Title y la URL de cada JSONObject
                                        Photo photo = new Photo();
                                        if (p.has("title"))
                                            photo.setTitle(p.getString("title"));
                                        if (p.has("url"))
                                            photo.setImageUrl(p.getString("url"));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDataset.add(photo);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDataset.size() > 0)
                                        refreshDataset();
                                }
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        });

        //Agregamos la petición de tipo JSON a la cola
        queue.add(aRequest);
    }
}
